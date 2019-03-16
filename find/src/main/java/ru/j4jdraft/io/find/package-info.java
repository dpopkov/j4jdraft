/**
 <h3>Задание: Поиск файлов по критерию.</h3>

 1. Создать программу для поиска файла.<br>
 2. Программа должна искать данные в заданном каталоге и подкаталогах.<br>
 3. Имя файла может задаваться, целиком, по маске, по регулярному выражение(не обязательно).<br>
 4. Программа должна собираться в jar и запускаться через
 java -jar find.jar -d c:/ -n *.txt -m -o log.txt
 Ключи <br>
 -d - директория в которой начинать поиск.<br>
 -n - имя файл, маска, либо регулярное выражение.<br>
 -m - искать по маске, либо -f - полное совпадение имени. -r регулярное выражение.<br>
 -o - результат записать в файл.<br>
 5. Программа должна записывать результат в файл.<br>
 6. В программе должна быть валидация ключей и подсказка.<br>
 */
package ru.j4jdraft.io.find;