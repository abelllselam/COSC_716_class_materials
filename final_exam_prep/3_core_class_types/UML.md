**UML**

- It stands for Unified modeling language.
  +----------------------+
  | ClassName | ← 1) Top: the class’s name (centered, bold)
  +----------------------+
  | – attribute: Type | ← 2) Middle: attributes (fields)
  | # count: int |  
  +----------------------+
  | + operation(): void | ← 3) Bottom: methods (operations)
  | – helper(): String |
  +----------------------+

2. Visibility Symbols
   Each attribute or method is prefaced by a visibility marker:
   | Symbol | Name | Means… |
   | :----: | --------- | ------------------------------------------ |
   | + | public | Accessible everywhere |
   | – | private | Accessible only within the class itself |
   | # | protected | Accessible within the class and subclasses |
   | \~ | package | Accessible within the same package/module |

- Example:
  +------------------+
  | Person |
  +------------------+
  | – name: String |
  | – age: int |
  +------------------+
  | + getName():String |
  | + setName(n:String):void |
  +------------------+
  Person has two private attributes (name, age) and two public methods.
