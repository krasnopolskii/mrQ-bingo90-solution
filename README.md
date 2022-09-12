# mrQ-bingo90-solution

This solution generates 10k strips in around 350-390 mills 

It generates strips with no single error (no regeneration needed), 

Assume N is a bingo range (available numbers) and M is a strip size and P is a number of columns and Q is a number of rows (total in strip) than time complexity is O(n * (m + p + q))

```
attempts: 100
totalTime: 37364
avgTime for 1 strip: 373
random strip:
4  _ 23  _ 43  _ 61  _ 81
7  _ 26 31  _ 51  _ 76  _
_ 16  _ 33  _ 59  _ 77 89

_ 13 27  _ 40  _ 66  _ 80
8  _ 28  _ 46  _ 68  _ 87
9  _ 29  _ 48  _ 69  _ 90

1  _ 24  _ 42 52 65  _  _
_ 15  _ 32  _ 53  _ 72 84
_ 18 25  _ 44  _ 67 74  _

5 11  _ 35 47  _ 60  _  _
6  _ 21  _ 49  _ 63 70  _
_ 12  _ 37  _ 58  _ 71 82

3  _ 20 30 45  _ 62  _  _
_ 17  _ 38  _ 55  _ 78 85
_ 19  _ 39  _ 57  _ 79 86

_ 10  _ 34  _ 50  _ 73 83
_ 14  _ 36  _ 54  _ 75 88
2  _ 22  _ 41 56 64  _  _
```