# 1、标题

# 一级标题

## 二级标题

### 三级标题

#### 四级标题

##### 五级标题

##### 六级标题

# 2、文本操作

## 换行

我是一段很普通的信息，直接打出来就可以了。
（第一行后面两个空格换行）

---

## 分割线

## 加粗

**加粗**
__加粗__

## 斜体

*斜体*
***斜体加粗***
___斜体加粗___

## 删除线

~~删除线~~
***~~斜体 + 加粗 + 删除线~~***

## 下划线

`<u>`下划线`</u>`

# 3、列表

## 无序列表

* 无序列表1

+ 无序列表2

- 无序列表3

**但是注意三种符号不能混用（会认为不是同样的列表）**

## 有序列表

1. 有序列表1
   * 嵌套列表1
     * 嵌套列表2
2. 有序列表2
3. 有序列表3

## 任务列表

* [ ] 我是第一项任务
* [ ] 我是第二项任务
* [ ] 我是第三项任务

# 4、代码块

## 缩进四个空格（可以生成代码块）

```java
public class main {
    pbulic static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
```

## **```也可以生成代码块（推荐）：**

```c
#include <stdio.h>

int main() {
    printf("Hello World!");

    return 0;
}
```

`这是行内代码块`

# 5、引用

## 这是引用的内容1

> ```python
> print("Hello World")
> ```

## 这是引用的内容2

> * 无序列表
>   * 嵌套无序列表

## 这是引用的内容3

>> 嵌套引用第一层
>>
>>> 嵌套引用第二层
>>>
>>

# 6、链接

## 普通链接

这是一个[链接](www.baidu.com)

## 多链接

[想要][变量a]购买[xxx][变量b]前往[官网][变量c]

[变量a]: www.baidu.com
[变量b]: www.binyin.com
[变量c]: www.taobao.com
## 脚注

我们是[^1]脚注[^2]

[^1]: 脚注1
    
[^2]: 脚注2
    
# 7、图片

## 插入web图片

![插入web图片](https://gitee.com/ddgmma/my-images/releases/download/v1.0/red-star.png)

## 插入本地图片

![插入本地图片“相对路径”](test.jpg)

![插入本地图片“绝对路径”](C:/Users/ddgmma/Pictures/壁纸/bizhihui.jpg)

<!-- 提示：在vscode内可能不生效 -->

# 8、表格

| 姓名 | 性别 | 年龄 |
| ---- | ---- | ---- |
| 张三 | 男   | 18   |
| 李四 | 男   | 19   |
| 王二 | 女   | 20   |

| 默认 | :居中: | :左对齐 | 右对齐: |
| ---- | :----: | :------ | ------: |
| 1    |   2   | 3       |       4 |

# 9、HTML支持

## 图片标签

<img style="width:100px;height:100px;" src="https://gitee.com/ddgmma/my-images/releases/download/v1.0/1-num.png">

## 换行、分割线标签

我是一段很普通的信息，直接打出来就可以了。
`<br>`
换行。

<hr>
分割线

## 超链接标签

`<a href="https://www.baidu.com">`百度一下`</a>`

## 行内标签

这是一段普通的文本，直接`<span style="color:red;font-size:30px">`打出来就可以了。

# Markdown拓展语法

## 高亮

==我是高亮文本==

## 角标

上角标^1^
下角标~1~

# 数学公式

## 单行

$x = 1 + y$

## 多行

**双反斜杠 \\\ 可以换行**

$$
\frac{1}{2} = x^2 + y_2 + z^2_2 + x^{2x} + \sqrt[x]{y} + \{x + 1\} \\
z = 2 \times x \\
\int^1_0x^2dx \\
\lim_{n\rightarrow+\infty}\frac{1}{n}
$$

## 数学符号

$$
\not= 不等于 \\
\approx 约等于 \\
\leq 小于等于 \\
\geq 大于等于 \\
\times 乘号 \\
\div 除号 \\
\pm 正负号 \\
\sum 求和符号 \\
\overline{1 + 2 = 3} 求平均值 \\
90^\circ 度数 \\
\sin \\
\cos \\
\cot \\
\pi \\
\infty 无穷 \\
\int 定积分 \\
\iint 双重积分 \\
\prime 求导 \\
\lim 极限 \\
\cdots 省略号 \\
\cdot \\
\ldots 底部省略号 \\
等等还有很多，自己搜
$$
