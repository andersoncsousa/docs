#!usr/bin/python
# -*- coding:utf-8 -*-

import os

try:
    #limpa tela
    os.system('cler')
    user1 = str(input('Quantas horas tem um dia?')).lower()
    point = 5
    erros = 0
    def over(point,erros):
        if point == 0:
            print ('Fim de Jogo!')
            raise SystemExit
        elif erros >=5 and erros <=10:
            print ('Voce esta errando muito')
        elif erros >= 11:
            print ('Fim de Jogo!')
            raise SystemExit

    while True and user1 != '24':
        user1 = str(input('Quantas horas tem um dia?')).lower()
        point -=1
        erros +=1
        print('Seus pontos',point)
        print('Erros',erros)
        over(point,erros)
    else:
        point +=2
        print('Seus pontos',point)
        print('Erros',erros)

        user2 = str(input('Qual a capital do Brasil?')).lower()
        while True and user2 !='brasilia':
            point -=1
            erros +=1
            print('Seus pontos',point)
            print('Erros',erros)
            over(point,erros)
        else:
            print('Você acertou!!')
except KeyboardInterrupt:
    print ('\nControl-c precionado')
