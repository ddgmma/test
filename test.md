# 第 4 章 课堂练习 —— 实验四：磁盘和文件系统管理

## 实验目的

1. 掌握磁盘的添加、分区、格式化、挂载
2. 掌握磁盘阵列的管理
3. 掌握 LVM 的管理

## 实验内容

### （一）基础知识

**Linux 磁盘及分区的表示方法**

**Linux 文件系统**

```bash
cat /etc/filesystems
```

**swap**

**查看分区信息**

```bash
fdisk -l
```

### （二）磁盘操作

**添加新硬盘**

给虚拟机添加 5 块新硬盘，重启后才能识别新硬盘。

**给新硬盘分区**

```bash
fdisk /dev/sdb
```

- `n: p` — 主分区，5G
- `n: e` — 扩展分区（余下的所有空间）
- `n: l` — 两个逻辑分区：`sdb5` 8G，`sdb6`
- `w` — 写入分区信息并退出

**查看系统内核是否已识别新分区**

```bash
cat /proc/partitions
```

**格式化分区**

```bash
mkfs -t xfs /dev/sdb1
mkfs -t ext4 /dev/sdb5
mkfs.xfs -f /dev/sdb6
```

**显示所有可用块设备的文件系统**

```bash
lsblk -f
```

### （三）挂载

**查看挂载设备**

```bash
df -h
```

**挂载操作**

```bash
mkdir /data
mount /dev/sdb1 /data

mkdir /mnt/{game,movie}
mount /dev/sdb5 /mnt/game
mount /dev/sdb6 /mnt/movie
```

**查看挂载结果**

```bash
df -hT | grep -v "tmpfs"
```

**设置开机自动挂载**

编辑 `/etc/fstab` 文件：

```
/dev/sdb1  /data      xfs   defaults  0  0
/dev/sdb5  /mnt/game  ext4  defaults  0  0
/dev/sdb6  /mnt/movie xfs   defaults  0  0
```

使 `/etc/fstab` 中的挂载立即生效：

```bash
mount -a
```

重启后用 `lsblk -f` 查看可用的块设备。

### （四）磁盘配额

#### 启用磁盘配额

以 `/dev/sdb1` 的挂载点 `/data` 为例：

编辑 `/etc/fstab`：

```
/dev/sdb1  /data  xfs  defaults,uquota,gquota  0  0
```

检查配额是否生效：

```bash
mount | grep sdb1
```

启用磁盘配额后，需重新挂载才会生效：

```bash
umount /data
mount -a
mount | grep sdb1
```

#### 设置用户配额

设置用户 `hd` 的磁盘容量硬限制为 100MB，文件数量硬限制为 3 个：

```bash
edquota -u hd
```

设置用户组 `hd` 的磁盘容量硬限制为 200MB，文件数量硬限制为 6 个（只对基本组生效）：

```bash
edquota -g hd
```

添加新用户 `jerry`，指定其基本组为 `hd`：

```bash
useradd -g hd jerry
```

#### 测试配额

生成测试文件：

```bash
dd if=/dev/zero of=/tmp/test1 bs=1M count=60
dd if=/dev/zero of=/tmp/test2 bs=1M count=10
```

设置 `/data` 权限：

```bash
chmod 777 /data
```

**切换到 `hd` 验证：**

```bash
cp /tmp/test1 /data/a
cp /tmp/test1 /data/b       # 这里是否报错？为什么？
ll -h /data                 # 查看 a 和 b 是否存在？大小是多少？
```

此时 `hd` 的磁盘配额已用完，需删除 `b` 文件后再做后续测试：

```bash
rm /data/b
cp /tmp/test2 /data/b
cp /tmp/test2 /data/c
cp /tmp/test2 /data/d       # 这里是否报错？为什么？
```

**再用 `jerry` 验证：**

```bash
cp /tmp/test1 /data/a1
cp /tmp/test1 /data/a2      # 这里是否报错？为什么？
cp /tmp/test2 /data/a2
cp /tmp/test2 /data/a3
cp /tmp/test2 /data/a4      # 这里是否报错？为什么？
```

#### 查看配额使用情况

```bash
quota -u hd
quota -g hd
repquota /data
```
