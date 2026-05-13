# hello.py
# 这是一个输出空心星号金字塔的程序

n = int(input("请输入金字塔层数: "))

for i in range(1, n + 1):
    if i == 1:
        # 第一行：只有一个星号
        print(" " * (n - i) + "*")
    elif i == n:
        # 最后一行：全部星号
        print("*" * (2 * i - 1))
    else:
        # 中间行：左右两个星号，中间空格
        left_spaces = " " * (n - i)
        middle_spaces = " " * (2 * i - 3)
        print(left_spaces + "*" + middle_spaces + "*")