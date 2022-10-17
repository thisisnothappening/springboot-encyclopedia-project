# Java-Encyclopedia-Project-Backend

Nume: Paul Negoiu
Titlu: WelcomePedia / Enciclopedie Web

Am facut o enciclopedie web care afiseaza diverse articole, folosind Java pentru back-end si Typescript pentru front-end.
In front-end: 
- se poate da click pe un articol, care va face sa apara sub el article.text (adica informatii despre articol) si 2 butoane, unul 'DELETE' si unul 'EDIT'
- in header se poate vedea titlul "WelcomePedia", sub care exista 2 filtre. Cel din stanga este pentru Category, si cel din dreapta pentru Article
- in dreapta titlului se afla butonul 'ADD'. Cand se da click pe buton, el dispare, si in locul lui apare butonul 'CLOSE'. De asemenea, filtrele dispar, si in locul lor apare form-ul de adaugare a unui nou articol
- cand se da click pe 'CLOSE', reapare butonul de 'ADD' si se inchide orice form, iar filtrele reapar
- cand se da click pe butonul 'DELETE', bineinteles ca articolul se sterge
- cand se da click pe butonul 'EDIT', el dispare, odata cu filtrele, si apare form-ul de edit la acel articol
- dupa ce am dat click pe 'ADD' sau 'EDIT', si am scris ceva in fiecare field din form, se poate da click pe 'SAVE', care inchide form-ul, si adauga sau updateaza un articol
