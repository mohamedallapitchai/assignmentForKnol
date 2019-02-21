# Point of Sale

### Problem Statement
```
Consider a store where items have prices per unit but also volume prices. For example, apples may be $1.00 each or 4 for $3.00. Implement a point-of-sale scanning API that accepts an arbitrary ordering of products (similar to what would happen at a checkout line) and then returns the correct total price for an entire shopping cart based on the per unit prices or the volume prices as applicable.

Here are the products listed by code and the prices to use (there is no sales tax):

Ensure that the assignment is submitted with working test cases

Product Code	Price
A	$2 each or 4 for $7
B	$12
C	$1.25 each or $6 for a six-pack
D	$.15
There should be a top level point of sale terminal service object that looks something like the pseudo-code below. You are free to design and implement the rest of the code however you wish, including how you specify the prices in the system:

terminal.setPricing(...) 
terminal.scan("A") 
terminal.scan("C") 
... etc. 
result = terminal.total

Here are the minimal inputs you should use for your test cases. These test cases must be shown to work in your program:
Scan these items in this order: ABCDABAA; Verify the total price is $32.40.
Scan these items in this order: CCCCCCC; Verify the total price is $7.25.
Scan these items in this order: ABCD; Verify the total price is $15.40.
```
### Solution
##### Steps to compile, test and run point of sale program:

1. Clone the repo using the command `git clone https://github.com/mohamedallapitchai/assignmentForKnol.git`
2. Change the directory to `assignmentForKnol`
3. Run command `sbt compile` (Assumed SBT installed in your machine. If not then install it first)
4. Once compiled successfully then run the test cases by `sbt test`
5. To run the program enter the command `sbt run`

##### Running the Program:

There are two phases in providing the input.
1) Constructing the product list for the items
2) Scanning the items

You will be instructed by the program to provide input to construct the price list first and then scan the items.

##### Input format for product list creation:

`ProductName:[A-D],UnitPrice:Double,VolumeQty:Int,VolumePrice:Double`

**Example**

`A,12.5,3,1.5`

where **A** is the _product Name_, **12.5** is the _unit price_, **3,1.5** means _3 for $1.5_

**Note**:

- The **product name** and the **unit price** are mandatory. If the **volume quantity** and the **volume price**
not given then they are treated not existing.
- If **volume price** exists then there should also exists the **volume quantity** and vice versa.
- Currently only **A to D** products are supported by the program

***

#### Sample Input and Output:

```
Mohameds-MBP:assignment-Knol mohamedallapitchai$ sbt run
[info] Done packaging.
[info] Running TerminalApp 
Assignment running
Enter product list into the terminal - [enter `d` or `done`] to exit
Product List Format: ProductName:[A-D],UnitPrice:Double,VolumeQty:Int,VolumePrice:Double
example:A,12.5,3,1.5
A,2,4,7
Product A captured - Enter next product - Enter d or done to scan items
B,12
Product B captured - Enter next product - Enter d or done to scan items
C,1.25,6,6
Product C captured - Enter next product - Enter d or done to scan items
D,.15
Product D captured - Enter next product - Enter d or done to scan items
d
Now scan items one by one - Enter $ to end:
A
scanned product is A - type $ to exit
B
scanned product is B - type $ to exit
C
scanned product is C - type $ to exit
D
scanned product is D - type $ to exit
A
scanned product is A - type $ to exit
B 
scanned product is B - type $ to exit
A
scanned product is A - type $ to exit
A
scanned product is A - type $ to exit
$
Total: 32.4
[success] Total time: 53 s, completed Feb 20, 2019, 7:44:13 PM

```



