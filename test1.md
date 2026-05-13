```markdown
# 第4章 课堂练习 ------ 实验四：磁盘和文件系统管理

## 实验目的

1. 掌握磁盘的添加、分区、格式化、挂载
2. 掌握磁盘阵列的管理
3. 掌握LVM的管理

## 实验内容

### （一）基础知识

1. Linux磁盘及分区的表示方法
2. Linux文件系统：
    1) `Cat /etc/filesystems`
    2) swap
3. 查看分区信息：`fdisk -l`
4. 给虚拟机添加5块新硬盘，重启后才能识别新硬盘
5. 给新硬盘分区：`fdisk /dev/sdb`
    1) n: p，主分区，5G
    2) n: e，扩展分区（余下的所有空间）
    3) n: l，两个逻辑分区：sdb5 8G，sdb6
    4) w ，写入分区信息并退出
    5) 查看系统内核是否已经识别出新分区：`cat /proc/partitions`
6. 格式化分区
    1) `mkfs -t xfs /dev/sdb1`
    2) `mkfs -t ext4 /dev/sdb5`
    3) `mkfs.xfs -f /dev/sdb6`
    4) 显示所有可用的块设备的文件系统：`lsblk -f`
7. 挂载
    1) 查看挂载设备：`df -h`，看有无sdb
    2) 将新建目录/data，挂载到/dev/sdb1：`mount /dev/sdb1 /data`
    3) `mkdir /mnt/{game,movie}`
```
        mount /dev/sdb5 /mnt/game
        mount /dev/sdb6 /mnt/movie
        ```
    4）查看挂载：`df -hT | grep -v "tmpfs"`
    5）上述的挂载在重启时会自动被卸载，可修改/etc/fstab文件让系统自动挂载，如：
        ```
        /dev/sdb1 /data xfs defaults 0 0
        /dev/sdb5 /mnt/game ext4 defaults 0 0
        /dev/sdb6 /mnt/movie xfs defaults 0 0
        ```
    6) 让/etc/fstab文件中的挂载立即生效：`mount -a`
    7) 重启，用`lsblk -f`查看可用的块设备
8. 磁盘配额
    1) 启用磁盘配额：以/dev/sdb1的挂载点 /data 为例
        ```
        # vim /etc/fstab
        /dev/sdb1 /data xfs defaults,uquota,gquota 0 0
        mount | grep sdb1   # 磁盘配额生效了没有？
        ```
        启用磁盘配额后，要重新挂载才会生效
        ```
        umount /data   # 先卸载
        mount -a       # 再挂载
        mount | grep sdb1   # 查看
        ```
    2) 设置用户hd的磁盘容量硬限制额为100MB，文件数量硬限制额为3个
        ```
        [root@localhost ~]# edquota -u hd
        ```
    3) 设置用户组hd的磁盘容量硬限制额为200MB，文件数量硬限制额为6个（只对基本组生效）
        ```
        [root@localhost ~]# edquota -g hd
        ```
    4) 添加新用户jerry，指定其基本组为hd
        ```
        [root@localhost ~]# useradd -g hd jerry
        ```
    5) 生成一个60MB的测试文件：
        ```
        # dd if=/dev/zero of=/tmp/test1 bs=1M count=60
        ```
    6) 生成一个10MB的测试文件：
        ```
        # dd if=/dev/zero of=/tmp/test2 bs=1M count=10
        ```
    7) 设置/data权限：
        ```
        # chmod 777 /data
        ```
    8) 切换到hd验证：
        ```
        $ cp /tmp/test1 /data/a
        $ cp /tmp/test1 /data/b    （这里是否报错？为什么？）
        $ ll -h /data   # 查看/data目录下a和b两个是否存在？大小是多少？
        # 此时，hd的磁盘配额已用完，要删除b文件后，再做后面的测试
        $ rm /data/b
        $ cp /tmp/test2 /data/b
        $ cp /tmp/test2 /data/c
        $ cp /tmp/test2 /data/d    （这里是否报错？为什么？）
        ```
    9) 再用jerry验证：
        ```
        [jerry@localhost ~]$ cp /tmp/test1 /data/a1
        [jerry@localhost ~]$ cp /tmp/test1 /data/a2    （这里是否报错？为什么？）
        [jerry@localhost ~]$ cp /tmp/test2 /data/a2
        [jerry@localhost ~]$ cp /tmp/test2 /data/a3
        [jerry@localhost ~]$ cp /tmp/test2 /data/a4    （这里是否报错？为什么？）
        ```
    10) 查看用户或分区的配额使用情况：
        ```
        [root@localhost ~]# quota -u hd
        [root@localhost ~]# quota -g hd
        [root@localhost ~]# repquota /data
        ```

```