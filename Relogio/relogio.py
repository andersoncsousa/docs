#! usr/bin/env python
from tkinter import *
import tkinter as tk
from time import strftime as time

rel = tk.Label(text = time("%H:%M:%S"), font="Helvetica 120 bold")
rel.pack()

def relogio():
    agora = time("%H:%M:%S")
    if rel['text'] != agora:
        rel['text'] = agora
    rel.after(100,relogio)

relogio()
rel.mainloop()
