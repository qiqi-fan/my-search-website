import jieba
while True:
    str=input()
    str_list = jieba.cut_for_search(str)
    for e in str_list:
        print(e)
