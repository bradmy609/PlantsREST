import codecs

from nbformat import write

with open("src/main/resources/data.sql", "r", encoding="utf-8") as f:
    old = f.read()
    list_of_words = old.split(" ")


n = open("poop.sql", "w")
for word in list_of_words:
    if word[0] == "(":
        n.write("(")
    else:
        try:
            n.write(word.encode('utf-8').decode('utf-8', 'replace') + " ")
        except Exception as e:
            bytestring = word.encode("utf-8")
            stringBytes = str(bytestring).replace('"', "").replace("b", "")
            n.write(stringBytes)
            bytearr = bytearray(word, encoding="utf-8")
            print(bytearr.replace(b'\\x81', b'\\x79'))
            
