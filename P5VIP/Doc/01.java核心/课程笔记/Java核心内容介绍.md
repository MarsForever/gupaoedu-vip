# Java核心内容介绍

讲师:<span style='color:red'>波波老师</span>

> 1.集合
>
> 2.泛型
>
> 3.反射
>
> 4.注解



# 一.数据结构



### 1.数组

```java
Char[] cs = new Char[]{'G','U','P','A','O'};
Char[] cs1 = new Char[5];
cs1[0] = 'G';
....
```



特点：

> 1.内存地址连续，使用之前必须要指定数组长度
>
> 2.可以通过下标访问的方式访问成员，查询效率高
>
> 3.<span style='color:red'>增删</span>操作会给系统带来性能消耗[保证数据下标越界的问题，需要动态扩容]

### 2.链表

单向链表和双向链表

双向链表

特点

> 1.灵活的空间要求，存储空间不要求连续
>
> 2.不支持下标的访问，支持顺序遍历检索
>
> 3.针对<span style='color:red'>增删</span>效率会更高些，只和操作节点的前后节点有关系，无需移动元素。



LinkedList

```java
    private static class Node<E> {
        E item; // 节点的元素
        Node<E> next; // 下一个节点
        Node<E> prev; // 上一个节点

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
```





### 3.树



#### 红黑树

​    红黑树,Red-Black Tree [RBT] 是一个<b>自平衡</b>【不是绝对】的二叉查找树,树上的节点满足如下的规则

> 1.每个节点要么是红色，要么是黑色。
>
> 2.根节点必须是黑色
>
> 3.每个叶子节点【NIL】是黑色
>
> 4.每个红色节点的两个子节点必须是黑色
>
> 5.任意节点到每个叶子节点的路径包含相同数量的黑节点



黑平衡二叉树

> 1.recolor  重新标志节点为红色或者黑色
>
> 2.rotation 旋转  树达到平衡的关键



红黑树能自平衡，它靠的是什么？三种操作：左旋、右旋和变色


> 左旋：以某个结点作为支点(旋转结点)，其右子结点变为旋转结点的父结点，
>           右子结点的左子结点变为旋转结点的右子结点，左子结点保持不变。
> 右旋：以某个结点作为支点(旋转结点)，其左子结点变为旋转结点的父结点，
>           左子结点的右子结点变为旋转结点的左子结点，右子结点保持不变。
> 变色：结点的颜色由红变黑或由黑变红。



红黑树插入的场景

1.红黑树为空

2.父节点为黑色节点





# 二.集合



Collection接口

​       【,,,,,】

Map接口

​        KV对

Iterator 迭代



工具类：

​       Collections

​       Arrays



比较器

   Comparable  Comparator



## List接口



### 1.ArrayList

​     本质就是动态数组，动态扩容

```java
    /**
     * Default initial capacity.
       默认的数组的长度
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Shared empty array instance used for empty instances.
     空数组
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /**
     * Shared empty array instance used for default sized empty instances. We
     * distinguish this from EMPTY_ELEMENTDATA to know how much to inflate when
     * first element is added.
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * The array buffer into which the elements of the ArrayList are stored.
     * The capacity of the ArrayList is the length of this array buffer. Any
     * empty ArrayList with elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA
     * will be expanded to DEFAULT_CAPACITY when the first element is added.
       集合中存储数据的 数组对象
     */
    transient Object[] elementData; // non-private to simplify nested class access

    /**
     * The size of the ArrayList (the number of elements it contains).
     *  集合中元素的个数
     * @serial
     */
    private int size;
```







#### 初始操作

无参构造

```java
public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
        // this.elementData = {}
}
```



有参构造

```java
    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            // 初始长度大于0 就创建一个指定大小的数组
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            // {}数组赋值给 this.elementData
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
        }
    }
```







#### add方法

初始无参构造器

##### 第一次添加



```java
public boolean add(E e) {
    // 确定容量 动态扩容 size 初始 0
    ensureCapacityInternal(size + 1);  // Increments modCount!!
    // 将要添加的元素 添加到数组中 elementData[0] = 1 --> size = 1
    elementData[size++] = e;
    return true;
}
```





```java
private void ensureCapacityInternal(int minCapacity) {
        // ensureExplicitCapacity(10)
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
}
```





```java
/**
* elementData {}
  minCapacity 1
*/
private static int calculateCapacity(Object[] elementData, int minCapacity) {
    if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
        // 10  1 return 10
        return Math.max(DEFAULT_CAPACITY, minCapacity);
    }
    // 5
    return minCapacity;
}
```





```java
private void ensureExplicitCapacity(int minCapacity) {
    modCount++; // 增长 操作次数

    // minCapacity 10
    if (minCapacity - elementData.length > 0)
        grow(minCapacity);
}
```





```java
private void grow(int minCapacity) { // 10
    // overflow-conscious code
    int oldCapacity = elementData.length; // 0
    // newCapacity = 0
    int newCapacity = oldCapacity + (oldCapacity >> 1);
    if (newCapacity - minCapacity < 0)
        // newCapacity = 10 
        newCapacity = minCapacity;
    if (newCapacity - MAX_ARRAY_SIZE > 0)
        newCapacity = hugeCapacity(minCapacity);
    // {}   {,,,,,,,,,} 返回一个新的数组 长度为10
    elementData = Arrays.copyOf(elementData, newCapacity);
}
```



##### 第二次添加

```java
elementData = {1,,,,,,,,,};
size = 1;
```



```java
public boolean add(E e) {
    // 2
    ensureCapacityInternal(size + 1);  // Increments modCount!!
    elementData[size++] = e; // elementData[1] = 2  size = 2
    return true;
}
```





```java
private static int calculateCapacity(Object[] elementData, int minCapacity) {
    if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
        return Math.max(DEFAULT_CAPACITY, minCapacity);
    }
    // 2
    return minCapacity;
}
```





```java
private void ensureExplicitCapacity(int minCapacity) {
    modCount++;

    // overflow-conscious code 2 - 10
    if (minCapacity - elementData.length > 0)
        grow(minCapacity);
}
```





##### 第十一次添加



```java
elementData = {1,2,3,4,5,6,7,8,9,10};
size = 10;
```



```java
public boolean add(E e) {
    ensureCapacityInternal(size + 1);  // Increments modCount!!
    elementData[size++] = e;
    return true;
}
```



```java
private void ensureCapacityInternal(int minCapacity) {
    // ensureExplicitCapacity(11)
    ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
}
```



```java
private void ensureExplicitCapacity(int minCapacity) {
    modCount++;

    // 11 - 10 > 0
    if (minCapacity - elementData.length > 0)
        grow(minCapacity);
}
```





```java
private void grow(int minCapacity) { // 11
    // 10
    int oldCapacity = elementData.length;
    // 15  newCapacity 是oldCapacity的1.5倍
    int newCapacity = oldCapacity + (oldCapacity >> 1);
    if (newCapacity - minCapacity < 0)
        newCapacity = minCapacity;
    if (newCapacity - MAX_ARRAY_SIZE > 0)
        newCapacity = hugeCapacity(minCapacity);
    // minCapacity is usually close to size, so this is a win:
    // {1,2,3,4,5,6,7,8,9,10} -- > {1,2,3,4,5,6,7,8,9,10,,,,,}
    elementData = Arrays.copyOf(elementData, newCapacity);
}
```





#### get方法

```java
public E get(int index) {
    // 检查下标是否合法
    rangeCheck(index);
	// 通过下标获取数组对应的元素
    return elementData(index);
}
```





#### set方法

```java
public E set(int index, E element) {
    rangeCheck(index); // 检查下标
	// 获取下标原来的值
    E oldValue = elementData(index);
    elementData[index] = element;
    return oldValue;
}
```





#### remove方法

```java
public E remove(int index) {
    rangeCheck(index);

    modCount++;
    E oldValue = elementData(index);
	// 获取要移动的元素的个数 {1,2,3,4,5,6,7,8,9} // 3  size=9  index=3
    //  {1,2,3,5,6,7,8,9,null}
    int numMoved = size - index - 1; // 5
    if (numMoved > 0)
        // 源数组 开始下标 目标数组 开始下标 长度
        System.arraycopy(elementData, index+1, elementData, index,
                         numMoved);
    elementData[--size] = null; // clear to let GC do its work
	// 删除的节点对应的信息
    return oldValue;
}
```



#### FailFast机制

​     快速失败的机制，Java集合类为了应对并发访问在集合迭代过程中，内部结构发生变化的一种防护措施，这种错误检查的机制为这种可能发生错误通过抛出 java.util.ConcurrentModificationException



![1593410030182](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\1593410030182.png)





### 2.LinkedList

​       LinkedList是通过双向链表去实现的，他的数据结构具有双向链表的优缺点，既然是双向链表，那么的它的顺序访问效率会非常高，而随机访问的效率会比较低，它包含一个非常重要的私有内部静态类:Node

```java
private static class Node<E> {
    E item; // 节点的元素
    Node<E> next; // 下一个节点
    Node<E> prev;  // 上一个节点

    Node(Node<E> prev, E element, Node<E> next) {
        this.item = element;
        this.next = next;
        this.prev = prev;
    }
}
```



get方法：本质上还是遍历链表中的数据

```java
    Node<E> node(int index) {
        // assert isElementIndex(index);
		// index 和 长度的一半比较
        if (index < (size >> 1)) {
            Node<E> x = first;
            // 从头开始循环
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            // 从尾部开始循环
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }
```



set方法

```java
public E set(int index, E element) {
    checkElementIndex(index);// 检查下标是否合法
    Node<E> x = node(index); // 根据下标获取对应的node对象
    E oldVal = x.item; // 记录原来的值
    x.item = element; // 赋予新的值
    return oldVal; // 返回修改之前的值
}
```





### 3.Vector

和ArrayList很类似，都是以动态数组的形式来存储数据

Vector线程安全的

每个操作方法都加的有synchronized关键字，针对性能来说会比较大的影响，慢慢就被放弃了



Collections

可以增加代码的灵活度，在我们需要同步是时候就通过如下代码实现

```java
List syncList = Collections.synchronizedList(list);
```





本质上

```java
 public E get(int index) {
     synchronized (mutex) {return list.get(index);}
 }
public E set(int index, E element) {
    synchronized (mutex) {return list.set(index, element);}
}
public void add(int index, E element) {
    synchronized (mutex) {list.add(index, element);}
}
public E remove(int index) {
    synchronized (mutex) {return list.remove(index);}
}
```





## Set接口

### 1.HashSet

#### 概述

​    HashSet实现Set接口，由哈希表支持，它不保证set的迭代顺序，特别是它不保证该顺序永久不变，运行使用null。

```java
public HashSet() {
    map = new HashMap<>();
}
```



add方法

```java
public boolean add(E e) {
    return map.put(e, PRESENT)==null;
}
```

本质上是将数据保持在 HashMap中  key就是我们添加的内容，value就是我们定义的一个Object对象

#### 特点

​	  底层数据结构是哈希表，HashSet的本质是一个"没有重复元素"的集合，他是通过`HashMap`实现的.HashSet中含有一个HashMap类型的成员变量`map`.

### 2.TreeSet

#### 概述

​         基于TreeMap的 NavigableSet实现。使用元素的自然顺序对元素进行排序，或者根据创建 set 时提供的 Comparator进行排序，具体取决于使用的构造方法。 

```java
public TreeSet() {
    this(new TreeMap<E,Object>());
}
```



本质是将数据保存在TreeMap中，key是我们添加的内容，value是定义的一个Object对象。





## Map接口

Map集合的特点

> 1.能够存储唯一的列的数据(唯一，不可重复) Set
>
> 2.能够存储可以重复的数据(可重复) List
>
> 3.值的顺序取决于键的顺序
>
> 4.键和值都是可以存储null元素的

### TreeMap

本质上就是`红黑树`的实现

>1.每个节点要么是红色，要么是黑色。
>
>2.根节点必须是黑色
>
>3.每个叶子节点【NIL】是黑色
>
>4.每个红色节点的两个子节点必须是黑色
>
>5.任意节点到每个叶子节点的路径包含相同数量的黑节点

```java
 K key; // key
V value; // 值
Entry<K,V> left; // 左子节点
Entry<K,V> right; // 右子节点
Entry<K,V> parent; // 父节点
boolean color = BLACK; // 节点的颜色  默认是黑色
```



put为例

```java
    public V put(K key, V value) {
        // 将root赋值给局部变量  null
        Entry<K,V> t = root;
        if (t == null) {
            // 初始操作
            // 检查key是否为空
            compare(key, key); // type (and possibly null) check
			// 将要添加的key、 value封装为一个Entry对象 并赋值给root
            root = new Entry<>(key, value, null);
            size = 1;
            modCount++;
            return null;
        }
        int cmp;
        Entry<K,V> parent; // 父节点
        // split comparator and comparable paths
        Comparator<? super K> cpr = comparator; // 获取比较器
        if (cpr != null) {
            // 一直找到插入节点的父节点
            do {
                // 将root赋值给了parent
                parent = t;
                // 和root节点比较值得大小
                cmp = cpr.compare(key, t.key);
                if (cmp < 0)
                    // 将父节点的左子节点付给了t
                    t = t.left;
                else if (cmp > 0)
                    t = t.right; // 将父节点的右节点付给了t
                else
                    // 直接和父节点的key相等，直接修改值
                    return t.setValue(value);
            } while (t != null);
        }
        else {
            if (key == null)
                throw new NullPointerException();
            @SuppressWarnings("unchecked")
                Comparable<? super K> k = (Comparable<? super K>) key;
            do {
                parent = t;
                cmp = k.compareTo(t.key);
                if (cmp < 0)
                    t = t.left;
                else if (cmp > 0)
                    t = t.right;
                else
                    return t.setValue(value);
            } while (t != null);
        }
        // t 就是我们要插入节点的父节点 parent
        // 将我们要插入的key value 封装成了一个Entry对象
        Entry<K,V> e = new Entry<>(key, value, parent);
        if (cmp < 0)
            parent.left = e; // 插入的节点在parent节点的左侧
        else
            parent.right = e; // 插入的节点在parent节点的右侧
        fixAfterInsertion(e); // 实现红黑树的平衡
        size++;
        modCount++;
        return null;
    }

```





```java
/** From CLR */
private void fixAfterInsertion(Entry<K,V> x) {
    // 设置添加节点的颜色为 红色
    x.color = RED;
	// 循环的条件 添加的节点不为空  不是root节点  父节点的颜色为红色
    while (x != null && x != root && x.parent.color == RED) {
        // 父节点是否是 祖父节点的左侧节点
        if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
            // 获取父节点的 兄弟节点  叔叔节点
            Entry<K,V> y = rightOf(parentOf(parentOf(x)));
            if (colorOf(y) == RED) { // 叔叔节点是红色
                // 变色
                setColor(parentOf(x), BLACK); // 设置 父节点的颜色为黑色
                setColor(y, BLACK); // 设置叔叔节点的颜色为 黑色
                setColor(parentOf(parentOf(x)), RED); // 设置 祖父节点的颜色是 红色
                // 将祖父节点设置为 插入节点
                x = parentOf(parentOf(x));
            } else { // 叔叔节点是黑色
                if (x == rightOf(parentOf(x))) {
                    // 判断插入节点是否是 父节点的右侧节点
                    x = parentOf(x); // 将父节点作为插入节点
                    rotateLeft(x); // 左旋
                }
                setColor(parentOf(x), BLACK);
                setColor(parentOf(parentOf(x)), RED);
                rotateRight(parentOf(parentOf(x)));// 右旋
            }
        } else {// 父节点是祖父节点的右侧子节点
            // 获取叔叔节点
            Entry<K,V> y = leftOf(parentOf(parentOf(x)));
            if (colorOf(y) == RED) { // 叔叔节点为红色
                // recolor 变色
                setColor(parentOf(x), BLACK);
                setColor(y, BLACK);
                setColor(parentOf(parentOf(x)), RED);
                x = parentOf(parentOf(x));
            } else {
                // 插入节点在父节点的右侧
                if (x == leftOf(parentOf(x))) {
                    x = parentOf(x);
                    rotateRight(x); // 右旋
                }
                setColor(parentOf(x), BLACK);
                setColor(parentOf(parentOf(x)), RED);
                rotateLeft(parentOf(parentOf(x))); // 左旋
            }
        }
    }
    // 根节点的颜色为黑色
    root.color = BLACK;
}

```



### HashMap

> HashMap底层结构
>    Jdk1.7及以前是采用数组+链表
>    Jdk1.8之后 采用数组+链表  或者  数组+红黑树方式进行元素的存储
> 存储在hashMap集合中的元素都将是一个Map.Entry的内部接口的实现



```java
// 默认的HashMap中数组的长度 16
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
// HashMap中的数组的最大容量 
static final int MAXIMUM_CAPACITY = 1 << 30;
// 默认的扩容的平衡因子
static final float DEFAULT_LOAD_FACTOR = 0.75f;
// 链表转红黑树的 临界值
static final int TREEIFY_THRESHOLD = 8;
// 红黑树转链表的 临界值
static final int UNTREEIFY_THRESHOLD = 6
// 链表转红黑树的数组长度的临界值 
static final int MIN_TREEIFY_CAPACITY = 64;

// HashMap中的数组结构
transient Node<K,V>[] table;
// HashMap中的元素个数
transient int size;
// 对HashMap操作的次数
transient int modCount;
// 扩容的临界值
int threshold;
// 实际的扩容值
final float loadFactor;
```





put方法原理分析

```java
public V put(K key, V value) {
    return putVal(hash(key), key, value, false, true);
}
```



hash(key):获取key对应的hash值

```java
static final int hash(Object key) {
    int h;
    // key.hashCode() 32长度的二进制的值
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```

为什么要右移16位?

A:1000010001110001000001111000000

B:0111011100111000101000010100000

A 和 B 对 15  11111&预算 得到的都是 0 相同，会造成散列分布不均匀



```java
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        if ((tab = table) == null || (n = tab.length) == 0)
            // 初始的判断
            // resize() 初始数组  扩容 初始的时候 获取了一个容量为16的数组
            n = (tab = resize()).length; // n 数组长度
        // 确定插入的key在数组中的下标 15  11111
        // 100001000111000
        //            1111
        //            1000 = 8
        if ((p = tab[i = (n - 1) & hash]) == null)
            // 通过hash值找到的数组的下标  里面没有内容就直接赋值
            tab[i] = newNode(hash, key, value, null);
        else {
            Node<K,V> e; K k;
            if (p.hash == hash  // hash值相同&& 
                // key也相同
                ((k = p.key) == key || (key != null && key.equals(k))))
                // 插入的值的key 和 数组当前位置的 key是同一个 那么直接修改里面内容
                e = p;
            else if (p instanceof TreeNode)
                // 表示 数组中存放的节点是一个 红黑树节点
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                // 表示节点就是普通的链表
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        // 到了链表的尾部
                        p.next = newNode(hash, key, value, null);
                        // 将新的节点添加到了链表的尾部
                        // 判断是否满足 链表转红黑树的条件
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            // 转红黑树
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }
```



第一次resize()

```java
    final Node<K,V>[] resize() {
        // table = null
        Node<K,V>[] oldTab = table;
        // oldCap = 0
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        // 原来的扩容因子 0
        int oldThr = threshold;
        // 新的容量和新的扩容因子
        int newCap, newThr = 0;
        if (oldCap > 0) { // 初始不执行 0
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                     oldCap >= DEFAULT_INITIAL_CAPACITY)
                newThr = oldThr << 1; // double threshold
        }// 初始为0
        else if (oldThr > 0) // initial capacity was placed in threshold
            newCap = oldThr;
        else {  
            // 新的数组容量 16
            newCap = DEFAULT_INITIAL_CAPACITY;
            // 新的扩容因子 0.75 * 16 = 12
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {
            float ft = (float)newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                      (int)ft : Integer.MAX_VALUE);
        }// 更新了 扩容的临界值 12
        threshold = newThr;
        @SuppressWarnings({"rawtypes","unchecked"})
        // 创建了一个容量为16的Node数组
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        table = newTab; // 更新了table
        if (oldTab != null) {
            for (int j = 0; j < oldCap; ++j) {
                Node<K,V> e;
                if ((e = oldTab[j]) != null) {
                    oldTab[j] = null;
                    if (e.next == null)
                        newTab[e.hash & (newCap - 1)] = e;
                    else if (e instanceof TreeNode)
                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                    else { // preserve order
                        Node<K,V> loHead = null, loTail = null;
                        Node<K,V> hiHead = null, hiTail = null;
                        Node<K,V> next;
                        do {
                            next = e.next;
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            }
                            else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }
```





```java
    final void treeifyBin(Node<K,V>[] tab, int hash) {
        int n, index; Node<K,V> e;
        // tab为空 或者 数组的长度小于64
        if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY)
            resize(); // 扩容
        else if ((e = tab[index = (n - 1) & hash]) != null) {
            // 链表转红黑树的逻辑
            TreeNode<K,V> hd = null, tl = null;
            do {
                TreeNode<K,V> p = replacementTreeNode(e, null);
                if (tl == null)
                    hd = p;
                else {
                    p.prev = tl;
                    tl.next = p;
                }
                tl = p;
            } while ((e = e.next) != null);
            if ((tab[index] = hd) != null)
                hd.treeify(tab);
        }
    }
```





动态扩容

```java
    final Node<K,V>[] resize() {
        // [1,2,3,4,5,6,7,8,9,10,11,,,,]
        Node<K,V>[] oldTab = table;
        // 16
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        // 12
        int oldThr = threshold;
        int newCap, newThr = 0;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            // 新的容量是 原来容量的两倍
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                     oldCap >= DEFAULT_INITIAL_CAPACITY)
                // 扩容的临界值  原来的两倍 24
                newThr = oldThr << 1; // double threshold
        }
        else if (oldThr > 0) // initial capacity was placed in threshold
            newCap = oldThr;
        else {               // zero initial threshold signifies using defaults
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {
            float ft = (float)newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                      (int)ft : Integer.MAX_VALUE);
        }
        threshold = newThr;
        @SuppressWarnings({"rawtypes","unchecked"})
        // 创建的数组的长度是32
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        table = newTab;
        if (oldTab != null) { // 初始的时候是不需要复制的
            for (int j = 0; j < oldCap; ++j) {
                Node<K,V> e;
                if ((e = oldTab[j]) != null) {
                    oldTab[j] = null;
                    if (e.next == null)
                        // 数组中的元素就一个 找到元素在新的数组中的位置 赋值
                        newTab[e.hash & (newCap - 1)] = e;
                    else if (e instanceof TreeNode)
                        // 移动红黑树节点
                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                    else { // preserve order
                        // 普通的链表的移动
                        Node<K,V> loHead = null, loTail = null;
                        Node<K,V> hiHead = null, hiTail = null;
                        Node<K,V> next;
                        do {
                            next = e.next;
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            }
                            else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }
```





# 三.泛型

​		本质：参数化类型

泛型的擦除：

​      泛型只在编译阶段有效，编译之后JVM会采取`去泛型化`的措施.

​      泛型在运行阶段是没有效果





## 泛型通配符的介绍

### 1.无边界通配符

> <?> 通用的类型

```java
public class Demo02 {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("gupao");
        list1.add("bobo");
        list1.add("mic");
        loop(list1);
    }

    public static void loop(List<?> list){
        for (int i = 0; i < list.size() ; i++) {
            System.out.println(list.get(i));
        }
    }
}
```



### 2.上边界通配符

> < ? extends Number > 代表从Number往下的子类货孙类对象都可以使用

```java
public class Demo03 {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("gupao");
        list1.add("bobo");
        list1.add("mic");
        // loop(list1);
        List<Number> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
    }

    /**
     *  ?  extends Number
     *  通用的类型必须是Number及其子类
     * @param list
     */
    public static void loop(List<? extends Number> list){
        for (int i = 0; i < list.size() ; i++) {
            System.out.println(list.get(i));
        }
    }
}
```



### 3.下边界通配符

> <? super Integer> 代表从Integer 到Object所有的对象都可以

```java
public class Demo04 {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("gupao");
        list1.add("bobo");
        list1.add("mic");
         loop(list1);
        List<Number> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        loop(list2);
    }

    /**
     *  ?  super Number
     *  通用类型必须是Integer 到Object类型的对象
     * @param list
     */
    public static void loop(List<? super Number> list){
        for (int i = 0; i < list.size() ; i++) {
            System.out.println(list.get(i));
        }
    }
}
```





## 泛型的具体的使用

规则

   必须先声明再使用

泛型的声明是通过"<>"实现

约定泛型可以使用单个大写字母来表示 K E T V 等



### 泛型类

```java
public class PersonNew <T> {

    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public PersonNew(T t) {
        this.t = t;
    }
}
```



增加我们代码的灵活度

### 泛型方法

```java
public class Demo07 <K,V> {

    /**
     * 普通方法 可以使用 类中定义的泛型
     * @param k
     * @param v
     * @return
     */
    public K method1(K k,V v){
        return (K)null;
    }

    /**
     * 普通方法  使用方法中定义的泛型
     * @param t
     * @param v
     * @param <T>
     * @return
     */
    public <T> T method2(T t,V v){
        return (T)null;
    }

    /**
     * 在静态方法中我们没法使用 类中定义的泛型
     * @return
     */
    public static <K> K method3(){
        return null;
    }

```



### 泛型接口

```java
public interface CalGeneric <T> {

    T add(T a,T b);

    T sub(T a,T b);

    T mul(T a,T b);

    T div(T a,T b);
}
```



```java
public class CalIntegerGeneric implements  CalGeneric<Integer> {
    @Override
    public Integer add(Integer a, Integer b) {
        return null;
    }

    @Override
    public Integer sub(Integer a, Integer b) {
        return null;
    }

    @Override
    public Integer mul(Integer a, Integer b) {
        return null;
    }

    @Override
    public Integer div(Integer a, Integer b) {
        return null;
    }
}

```



# 四.反射

## 1.反射的定义

​      反向探知，在程序运行过程中动态的获取类的相关属性

> 这种动态获取类的内容以及动态调用对象的方法和获取属性的机制.就叫做JAVA的反射机制

反射的优缺点

> 优点
>
> ​       增加程序的灵活性，避免固有逻辑写死到程序中
>
> ​       代码相对简洁，可以提高程序的复用性

> 缺点
>
> ​     相比于直接调用反射有比较大的性能销毁
>
> ​    内部暴露和安全隐患

反射到底慢在哪里？

> 1.调用了native方法
>
> 2.每次newInstance都会做安全检查 比较耗时



## 2.反射的操作

### 2.1基本操作

#### 1.获取类对象的四种方式

```java
// 获取类对象的四种方式
Class<User> clazz1 = User.class;
Class<?> clazz2 = Class.forName("com.gupao.edu.fs.User");
Class<? extends User> clazz3 = new User().getClass();
Class<?> clazz4 = Demo03.class.getClassLoader().loadClass("com.gupao.edu.fs.User");
```





#### 2.基本信息操作

```java
// 获取类的相关结构
System.out.println(clazz1.getModifiers()); // 获取类的修饰符
System.out.println(clazz1.getPackage());
System.out.println(clazz1.getName());
System.out.println(clazz1.getSuperclass());
System.out.println(clazz1.getClassLoader());
System.out.println(clazz1.getSimpleName());
System.out.println(clazz1.getInterfaces().length); // 获取类似实现的所有的接口
System.out.println(clazz1.getAnnotations().length);
```





### 2.2字段的操作

```java
   /**
     * Field操作
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Class<User> userClass = User.class;
        // 获取User对象
        User user = userClass.newInstance();
        // 获取类型中定义的字段 共有的字段以及父类中共有的字段
        Field[] fields1 = userClass.getFields();
        for(Field f:fields1){
            System.out.println(f.getModifiers() + " " + f.getName());
        }
        System.out.println("--------------------");
        // 可以获取私有的字段  只能够获取当前类中
        Field[] fields2 = userClass.getDeclaredFields();
        for(Field f:fields2){
            System.out.println(f.getModifiers() + " " + f.getName());
        }

        // 获取name字段对应的Field
        Field nameField = userClass.getDeclaredField("name");
        // 如果要修改私有属性信息那么我们需要放开权限
        nameField.setAccessible(true);
        nameField.set(user,"咕泡");
        System.out.println(user.getName());
        // 如果对静态属性赋值
        Field addressField = userClass.getDeclaredField("address");
        addressField.set(null,"湖南长沙");
        System.out.println(User.address);

    }
```

### 2.3 类中的方法操作

```java
    public static void main(String[] args) throws Exception {
        User user = new User();
        Class<User> userClass = User.class;
        // 可以获取当前类及其父类中的所有的共有的方法
        Method[] methods = userClass.getMethods();
        for (Method m : methods) {
            System.out.println(m.getModifiers() + " " + m.getName());
        }
        System.out.println("**********");
        // 获取本类中的所有的方法 包括私有的
        Method[] declaredMethods = userClass.getDeclaredMethods();
        for (Method m:declaredMethods){
            System.out.println(m.getModifiers() + " " + m.getName());
        }
        Method jumpMethod = userClass.getDeclaredMethod("jump");
        // 放开私有方法的调用
        jumpMethod.setAccessible(true);
        jumpMethod.invoke(user);
        Method sayMethod = userClass.getDeclaredMethod("say", String.class);
        // 静态方法调用
        sayMethod.invoke(null,"咕泡666");
    }
```



### 2.4 构造器的操作

```java
    /**
     * 构造器的操作
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Class<User> userClass = User.class;
        // 获取所有的公有的构造器
        Constructor<?>[] constructors = userClass.getConstructors();
        for (Constructor c:constructors){
            System.out.println(c.getModifiers() + " " + c.getName() );
        }
        System.out.println("************************");
        // 获取所有的构造器
        Constructor<?>[] declaredConstructors = userClass.getDeclaredConstructors();
        for (Constructor c:declaredConstructors){
            System.out.println(c.getModifiers() + " " + c.getName() );
        }
        // 1.直接通过newInstance创建对象
        User user = userClass.newInstance();
        // 2.获取对应的Construcator对象获取实例
        Constructor<User> declaredConstructor = userClass.getDeclaredConstructor(String.class, String.class);
        // 私有的构造器调用需要放开权限
        declaredConstructor.setAccessible(true);
        System.out.println(declaredConstructor.newInstance("gupao","男"));


    }
```





## 3.单例的漏洞

产生的原因是：反射可以调用私有的构造器造成的

```java
public class PersonSingle {

    private static PersonSingle instance;

    private PersonSingle(){
        if(instance != null){
            throw new RuntimeException("实例已经存在了，不允许再创建...");
        }
    }

    public static PersonSingle getInstance(){
        if(instance == null){
            instance = new PersonSingle();
        }
        return instance;
    }
}
```



解决方案：在私有构造其中加入逻辑判断结合`RuntimeException`处理即可

```java
    public static void main(String[] args) throws Exception {
        PersonSingle p1 = PersonSingle.getInstance();
        PersonSingle p2 = PersonSingle.getInstance();
        PersonSingle p3 = PersonSingle.getInstance();
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        // 通过反射获取实例
        Constructor<? extends PersonSingle> declaredConstructor = p1.getClass().getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        System.out.println( declaredConstructor.newInstance());

    }
```





反射的使用场景：

​     1.jdbc封装

​     2.SpringIOC

​     3.JdbcTemplate

​     4.Mybatis

​     ....





## 4.反射的应用 SpringIOC

IOC 控制反转 就是一种设计思想，容器 管理对象

```java
try {
    		// 创建对应IOC容器对象
            DefaultListableBeanFactory beanFactory = this.createBeanFactory();
            beanFactory.setSerializationId(this.getId());
            this.customizeBeanFactory(beanFactory);
    		// 配置文件中的<bean> 会被解析封装为一个 BeanDefinition 
            this.loadBeanDefinitions(beanFactory);
            Object var2 = this.beanFactoryMonitor;
            synchronized(this.beanFactoryMonitor) {
                this.beanFactory = beanFactory;
            }
        } catch (IOException var5) {
            throw new ApplicationContextException("I/O error parsing bean definition source for " + this.getDisplayName(), var5);
        }
```





```java
// 加载配置问题  SAX
Document doc = this.doLoadDocument(inputSource, resource);
// 配置文件解析 BeanDefinition
return this.registerBeanDefinitions(doc, resource);
```





```java
public void refresh() throws BeansException, IllegalStateException {
        Object var1 = this.startupShutdownMonitor;
        synchronized(this.startupShutdownMonitor) {
            this.prepareRefresh();
            // 创建IOC容器对象 BeanFactory 同时解析配置文件
            ConfigurableListableBeanFactory beanFactory = this.obtainFreshBeanFactory();
            this.prepareBeanFactory(beanFactory);

            try {
                this.postProcessBeanFactory(beanFactory);
                this.invokeBeanFactoryPostProcessors(beanFactory);
                this.registerBeanPostProcessors(beanFactory);
                this.initMessageSource();
                this.initApplicationEventMulticaster();
                this.onRefresh();
                this.registerListeners();
                // 单例对象的实例化
                this.finishBeanFactoryInitialization(beanFactory);
                this.finishRefresh();
            } catch (BeansException var9) {
                if (this.logger.isWarnEnabled()) {
                    this.logger.warn("Exception encountered during context initialization - cancelling refresh attempt: " + var9);
                }

                this.destroyBeans();
                this.cancelRefresh(var9);
                throw var9;
            } finally {
                this.resetCommonCaches();
            }

        }
    }
```





```java
    public static <T> T instantiateClass(Constructor<T> ctor, Object... args) throws BeanInstantiationException {
        Assert.notNull(ctor, "Constructor must not be null");

        try {
            ReflectionUtils.makeAccessible(ctor);
            return ctor.newInstance(args);
        } catch (InstantiationException var3) {
            throw new BeanInstantiationException(ctor, "Is it an abstract class?", var3);
        } catch (IllegalAccessException var4) {
            throw new BeanInstantiationException(ctor, "Is the constructor accessible?", var4);
        } catch (IllegalArgumentException var5) {
            throw new BeanInstantiationException(ctor, "Illegal arguments for constructor", var5);
        } catch (InvocationTargetException var6) {
            throw new BeanInstantiationException(ctor, "Constructor threw exception", var6.getTargetException());
        }
    }
```





![1593487402340](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\1593487402340.png)





# 五.注解

## 1.注解的概念

注解: 说明程序的，给计算机看的

注释: 用文字描述程序，给程序员看的

```java
/**
*
*/
//
```

> 定义：
>
> ​		注解（Annotation），也叫元数据。一种代码级别的说明。它是<span style='color:red'>JDK1.5</span>及以后版本引入的一个特性，与类、接口、枚举是在同一个层次。它可以声明在包、类、字段、方法、局部变量、方法参数等的前面，用来对这些元素进行说明，注释。
>
> 作用分类：
>
> 1.编写文档：通过代码里标识的注解生成文档【生成文档doc文档】
>
> 2.代码分析：通过代码里标识的注解对代码进行分析【使用反射】
>
> 3.编译检查：通过代码里标识的注解让编译器能够实现基本的编译检查【Override】

Java注解命令行，指定UTF-8

> javadoc -d doc -encoding UTF-8 -charset UTF-8 User.java

## 2.JDK预定义的注解

> @Override:检查被该注解标注的方式是否是继承自父类【接口】
>
> @Deprecated: 该注解表示注释的内容过时
>
> @SuppressWarnings: 压制警告



```text
all to suppress all warnings （抑制所有警告）
boxing to suppress warnings relative to boxing/unboxing operations（抑制装箱、拆箱操作时候的警告）
cast to suppress warnings relative to cast operations （抑制映射相关的警告）
dep-ann to suppress warnings relative to deprecated annotation（抑制启用注释的警告）
deprecation to suppress warnings relative to deprecation（抑制过期方法警告）
fallthrough to suppress warnings relative to missing breaks in switch statements（抑制确在switch中缺失breaks的警告）
finally to suppress warnings relative to finally block that don’t return （抑制finally模块没有返回的警告）
hiding to suppress warnings relative to locals that hide variable（）
incomplete-switch to suppress warnings relative to missing entries in a switch statement (enum case)(忽略没有完整的switch语句)
nls to suppress warnings relative to non-nls string literals(忽略非nls格式的字符)
null to suppress warnings relative to null analysis(忽略对null的操作)
rawtypes to suppress warnings relative to un-specific types when using generics on class params(使用generics时忽略没有指定相应的类型)
restriction to suppress warnings relative to usage of discouraged or forbidden references
serial to suppress warnings relative to missing serialVersionUID field for a serializable class(忽略在serializable类中没有声明serialVersionUID变量)
static-access to suppress warnings relative to incorrect static access（抑制不正确的静态访问方式警告)
synthetic-access to suppress warnings relative to unoptimized access from inner classes（抑制子类没有按最优方法访问内部类的警告）
unchecked to suppress warnings relative to unchecked operations（抑制没有进行类型检查操作的警告）
unqualified-field-access to suppress warnings relative to field access unqualified （抑制没有权限访问的域的警告）
unused to suppress warnings relative to unused code  （抑制没被使用过的代码的警告）
```



```java
@SuppressWarnings("all")
public class AnnoDemo01 {

    @Override
    public String toString() {
        return "AnnoDemo01{}";
    }

    @Deprecated
    public void show1(){
        // 发现过时了，功能更不上需求了
    }

    public void show2(){
        // 功能更加强大的方法
    }

    public void demo(){
        show1(); // 不推荐使用，但是可以使用
        show2();
        Date date = new Date();
        date.getYear();
    }
}
```





## 3.自定义注解

格式

```java
// 元注解
public @interface 注解名称{
    // 属性列表
}
```

自定义的注解反编译后的内容

```java
public interface MyAnno extends java.lang.annotation.Annotation {

}
```



注解的本质其实就是一个接口，继承Annotation父接口

```java
/**
 * 注解的本质就是接口
 */
public @interface MyAnno {
    public String show();
}
```





属性：在接口中定义的抽象方法

> 返回结果必须是如下类型
>
> 1.基本数据类型
>
> 2.String类型
>
> 3.枚举类型
>
> 4.注解
>
> 5.以上类型的数组



属性赋值注意点：

> 1.如果定义的属性时，使用default关键字给属性默认初始值，可以在使用注解是不赋值
>
> 2.如果只有一个属性需要赋值，而且该属性的名称是`value`,那么在赋值时 `value`可以省略
>
> 3.数组赋值的时候，值使用`{}`包裹，如果数组中只有一个值，那么`{}`可以省略

```java
/**
 * 注解的本质就是接口
 */
public @interface MyAnno {

    String value();
    MyAnno2 show4();
    PersonEnum show5();
    String[] show3();
    //String name();
    //int age() default 18; // 指定默认值 在使用注解的时候没有给该属性赋值，那么就使用默认值

    /*String show1();*/
    /*int show2();
    String[] show3();
    MyAnno2 show4();
    PersonEnum show5();*/

}
```





```java
@MyAnno(value="bobo",show4 = @MyAnno2,show5 = PersonEnum.P1,show3 = "a")
public void show2(){
    // 功能更加强大的方法
}
```





## 4.元注解

JDK中给我们提供的4个元注解

> 1.@Target:描述当前注解能够作用的位置
>
> ​        ElementType.TYPE:可以作用在类上
>
> ​        ElementType.METHOD:可以作用在方法上
>
> ​        ElementType.FIELD:可以作用在成员变量上
>
> 2.@Retention: 描述注解被保留到的阶段
>
> ​         SOURCE < CLASS < RUNTIME
>
> ​         SOURCE:表示当前注解只在代码阶段有效
>
> ​         CLASS:表示该注解会被保留到字节码阶段
>
> ​         RUNTIME:表示该注解会被保留到运行阶段 JVM
>
> ​         自定义的注解：RetentionPolicy.RUNTIME
>
> 3.@Documented:描述注解是否被抽取到JavaDoc  api中
>
> 4.@inherited:描述注解是否可以被子类继承



```java
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MyAnno3 {
}
```





## 5.自定义注解的案例

```java
/**
 * 自定义注解
 *     该注解表面要执行哪个类中的哪个方法
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface InvokAnno {

    String className();
    String methodName();
}
```



```java
public class Student1 {
    public void show(){
        System.out.println("student1 show ....");
    }
}
```





```java
@InvokAnno(className = "com.gupao.edu.anno2.Student2",methodName = "show")
public class MyMain {

    public static void main(String[] args) throws Exception {
        // 获取类对象
        Class<MyMain> clazz = MyMain.class;
        // 获取类对象中的注解
        InvokAnno an = clazz.getAnnotation(InvokAnno.class);
        /**
         *  注解本质是 接口  获取到的其实是接口的实现
         *  public class MyInvokAnno implements InvokAnno{
         *
         *      String className(){
         *          return "com.gupao.edu.anno2.Student1";
         *      }
         *      String methodName(){
         *          return "show";
         *      }
         *  }
         */
        // 获取注解中对应的属性
        String className = an.className();
        String methodName = an.methodName();
        System.out.println(className);
        System.out.println(methodName);

        // 通过反射的方式实现接口的功能
        Class<?> aClass = Class.forName(className);
        Method show = aClass.getDeclaredMethod("show");
        // 方法的执行
        Object o = aClass.newInstance();
        show.invoke(o); // 执行对应的方法
    }
}
```

