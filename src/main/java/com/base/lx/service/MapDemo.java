package com.base.lx.service;


public class MapDemo<K,V> {
    class  Node<K, V> {
        /**
         * K hashmap的key
         * V hashmap的value
         * Node 用来实现数组+链表的
         */
        private K key;
        private V value;
        private Node<K,V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node(K key, V value,Node<K,V> next) {
            this.key = key;
            this.value = value;
            this.next=next;
        }
    }
    //DEFAULT_CAPACITY  不给初始容量默认为16
    final int DEFAULT_CAPACITY=16;

    //扩容因子为0.75
    final float LOAD_FACTOR=0.75f;
    private int size;

    //元素为链表的数组
    Node<K,V>[] buckets;

    /**
     *  不给初始容量默认为16
     * DEFAULT_CAPACITY=16
     */
    public MapDemo(){
        buckets=new Node[DEFAULT_CAPACITY];
        size=0;
    }

    /**
     *  给初始容量
     *  就按给定大小创建
     */
    public MapDemo(int capacity){
        buckets=new Node[capacity];
        size=0;
    }

    /**
     * hashmap是数组+链表 这个方法是返回元素在数组中的哪个位置，注意是数组
     * @param key
     * @param length
     * @return
     */
    private int getIndex(K key,int length){
        int hashCode=key.hashCode();
        int index=hashCode % length;
        return Math.abs(index);
    }

    /**
     * hashmap的添加元素，先看容量够不够，不够就先扩容然后再插入，够了就直接插入
     * 插入用的putVal方法
     * @param key
     * @param value
     */
    public void put(K key,V value){
        if(size>=buckets.length*LOAD_FACTOR) resize();
        putVal(key,value,buckets);
    }


    /**
     * 将元素插入map中数组或链表的方法
     * @param key
     * @param value
     * @param table
     */
    private void putVal(K key,V value ,Node<K,V>[] table){
        //现获取元素应该放在数组中几号桶里（数组的几号位置）
        int index=getIndex(key,table.length);
        //获得该位置的节点
        Node node=table[index];
        //如果节点为空 ，说明该位置上没有元素，就直接插入，并对size++
        if(node==null){
            table[index]=new Node<>(key,value);
            size++;
            return;
        }
        //如果节点不为空，就使用拉链法，也就是挂在元素后面
        while (node != null) {
            //先看看key的hashcode是否一样，一样的话再看字符串是否一样，都一样的话说明key相同直接覆盖
            if ((node.key.hashCode()==key.hashCode())
                    &&(node.key==key||node.key.equals(key))) {
                //key相同直接修改value
                node.value=value;
                return;
            }
            //获取链表中下一个Node节点，用while进行循环判断
            node=node.next;
        }
        //说明数组中该链表上没有与要put的key相同，就创建一个新的Node节点，将数组中对应位置上的节点的next指向自己
        Node newNode= new Node(key,value,table[index]);
        //将新建的节点放进数组中
        table[index]=newNode;
        //记录map的元素的个数
        size++;
    }


    /**
     * 这方法是扩容的
     */
    private void resize(){
        //直接扩容两倍
        Node<K,V>[] newBuckets=new Node[buckets.length*2];
        //调用rehash方法对map中元素重新散列并摆放
        rehash(newBuckets);
        buckets=newBuckets;
    }

    /**
     * 对当前桶数组中的元素重新进行散列
     * @param newBuckets
     */
    private void rehash(Node<K,V>[] newBuckets){
        //重置map大小
        size=0;
        //将元素放进扩容两倍大小的数组中
        for (int i = 0; i < buckets.length; i++) {
            //看节点是否为空，为空就跳过
            if(buckets[i]==null){
                continue;
            }
            Node<K,V> node=buckets[i];
            while (node!=null){
                //将元素放入新数组中
                putVal(node.key,node.value,newBuckets);
                //对下一个元素进行重新散列
                node=node.next;
            }
        }
    }


    /**
     * 根据key获取value
     * @param key
     * @return
     */
    public V get(K key){
        //获取该key在数组的几号位置
        int index=getIndex(key, buckets.length);
        //查看该数组是否为空
        if(buckets[index]==null)  return null;
        Node<K,V> node=buckets[index];
        //查找链表
        while (node!=null){
            //看该节点的key的hashcode和字符是不是与传进来的一样，完全相同就直接返回value
            if((node.key.hashCode()==key.hashCode())
                    &&(node.key==key||node.key.equals(key))){
                return node.value;
            }
            //不相同的话就查看链表中的下一个元素
            node=node.next;
        }
        return null;
    }


    /**
     * 返回map中元素的个数
     * @return
     */
    public int size(){
        return size;
    }
}


