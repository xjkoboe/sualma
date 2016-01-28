# sualma
A data language.


Sualma is (or is going to be):
* a nice and simple textual format for representing data, comparable to JSON or XML
* but at the same time a programming language
* internal representation (model classes) in different programming languages (Java, JavaScript, C++, ...)
* parser and writers to convert textual data into/from internal representation
* tools for transforming sualma data, e.g. by evaluating expressions.

## Two main goals:
* storing and exchanging strongly typed data, even across different languages
* transforming data, evaluating expressions (similar to a calculator or a spreadsheet)

## Features
In contrast to JSON, sualma is designed to support graph structures, not only trees, by adding 'references'.

The language also support operators, function calls, function definitions (including lambdas), etc.,
with a minimal amount of language constructs.

At the time of writing the type system is still simple, but we plan to make it very powerful (think Haskell).
This will be driven by pattern matching. 
It should be possible to express a data schema in the language itself.


##Examples

This is a simple list containing a string and a number:
```
    "John", 20
```

Sualma offers different ways to encode list. The list above can also be written as
```
    "John"
    20
```

Of course lists can be nested, as in
```
    "John", 20
    "Mary", 22
```
which is equivalent to
```
    ( "John", 20 ), ( "Mary", 22 )
```

Sualma understands operators (with the conventional operator precedence):
```
   10 * ( -2 + 7 )
```
(more about evaluating expressions later).

Object can have names (labels):
```
    name = "John", age = 20
```
Names can be used to refer to objects:
```
    n = 100
    n + 1
    n - 1
```
Evaluating this would give
```
    n = 100
    101
    99
```


## Contribute

If you would like to help in the development, please contact me at <wijnand.schepens@gmail.com>
