# markdowntohtml

Please find webservice project that converts markdown to html. The project is implemented using Java Spring Framework.
I used regex to search for patterns in markdown specification to convert. I used strategy design pattern to implement each conversion rule.
Following are pointers to the core functionality of the project.

## Starting point

Please consider convert() method in the following class as a starting point.
https://github.com/ashishpinninti/markdowntohtml/blob/master/src/main/java/com/ashishpinninti/markdowntohtml/services/MarkdownToHtmlConverterService.java

## Tests
https://github.com/ashishpinninti/markdowntohtml/blob/master/src/test/java/com/ashishpinninti/markdowntohtml/rules/MarkdownToHtmlConverterServiceTest.java

## Rules
https://github.com/ashishpinninti/markdowntohtml/tree/master/src/main/java/com/ashishpinninti/markdowntohtml/services/rules
