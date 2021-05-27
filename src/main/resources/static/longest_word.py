### longest_word.py
###
### Uitility script to find the longest word length in the database

filepath = 'words.txt'
maxLen = 0
cnt = 0
with open(filepath) as fp:
   line = fp.readline()
   cnt = 1
   while line:
       line = fp.readline()
       cnt += 1
       if len(line.strip()) > maxLen:
            maxLen = len(line.strip())

print(f"Total # of entries: {cnt}")
print(f"Max length: {maxLen}")