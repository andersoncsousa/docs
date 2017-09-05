from itertools import product

def allwords(chars, length):
    for letters in product(chars, repeat=length):
        yield ''.join(letters)

def main():
    letters = "abc"
    #tamanho minímo de letrar 3 e máximo 5
    for wordlen in range (3, 5):
        for word in allwords(letters, wordlen):
            print(word)

if __name__ =="__main__":
    main()