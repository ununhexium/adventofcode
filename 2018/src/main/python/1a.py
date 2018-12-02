with open("../resources/1.input.txt") as f:
    txt = f.readlines()

previous = set()
current = 0

iteration = 0
while True:
    iteration += 1
    for l in txt:
        # print(sorted(previous))
        if current in previous:
            break
        else:
            previous.add(current)
            current += int(l)
    else:
        print(iteration, len(previous), len(set(previous)), min(previous), max(previous))
        continue
    break

print(current)
