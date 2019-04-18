# PWIO-Fork-Join-2

Projekt for "Programowanie współbieżne w języku Java" classes. Main idea is to use Thread Join method. Second version of second one - this repository contains JavaFX library.

Temat problemu : Analiza wpływu podziału zadania na równoległe wątki przy pomocy Fork-Join przy rysowaniu regularnych fragmentów. Opis problemu: Oprogramowanie ma za zadanie wielokrotne rysowanie regularnego fragmentu za pomocą wielu równoległych wątków tworzonych za pomocą Fork-Join. Zespół przeprowadzi analizę wpływu ilości tworzonych wątków na potrzebny czas do utworzenia obrazków (także w porównaniu do braku podzielenia zadania na równoległe wątki.

Sposób realizacji: Na wejściu główny wątek ma argumenty określające ile ma równoległych wątków utworzyć za pomocą Fork-Join, oraz jaka jest zadana szerokość jednego kwadratu tworzonego przez każdy z wątków. Następnie każdy z utworzonych wątków przeprowadza obliczenia, dzięki którym narysuje na wyznaczonym obszarze swój obrazek regularny. Po ich wykonaniu główny wątek łączy (Join) utworzone przez niego wątki i pokazuje utworzony obrazek. Powinien znaleźć czas, jaki potrzebowały utworzone wątki na wykonanie swoich operacji. Następnie sam rysuje taki sam rysunek z takimi samymi parametrami i również znajduje czas, który potrzebował na wykonanie zadania. Te czasy zostają poddane analizie przez zespół w zależności od różnych parametrów wejściowych (szerokość jednego kwadratu i ilość kwadratów w wierszu oraz kolumnie).

 Flow
 1.   Wątek główny pobiera parametry co do zadanego obrazku.
 2.   Wątek główne tworzy zadaną ilość wątków pobocznych za pomocą Fork-Join.
 3.   Wątki poboczne za pomocą szerokości obrazka tworzy obrazek dla swojego miejsca na całym obrazku.
 4.   Wątki poboczne kończą obliczenia.
 5.   Wątek główny czeka na zakończenie działania wątków pobocznych i zapisuje czas, jaki zajęło im wykonanie zadania.
 6.   Wątek główny pokazuje obrazek, jaki utworzyły wątki poboczne.
 7.   Wątek główny sam wykonuje identyczne zadanie i oblicza czas, jaki potrzebował na jego wykonanie.
 8.   Na wyjściu powinny znaleźć się dwa identyczne obrazki.
 9.   Wątek główny kończy zadanie.
