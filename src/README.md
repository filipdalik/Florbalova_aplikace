# Florbalová Aplikace

Jednoduchý program v Javě na kompletní správu florbalových zápasů a týmů. Všechno se ovládá přes klikací okna (Swing).

## Co to umí?
* **Hlavní menu:** Základní rozcestník, odkud se dostaneš všude.
* **Výběr týmů před zápasem:** Navolíš si, kdo proti komu hraje. Hlídá to, abys nezvolil stejné týmy nebo tým, co nemá aspoň 6 hráčů.
* **Hlavní okno zápasu:** Běží tu stopky (přes samostatné vlákno), počítá se skóre a dají se klikat góly i asistence.
* **Tresty:** Můžeš dávat tresty na 2 nebo 4 minuty, vybírat reálné florbalové fauly a časomíra je hráčům sama odpočítává.
* **Historie a Statistiky:** Zápasy se po skončení zapíšou do textového logu a v aplikaci se dá prohlížet tabulka kanadského bodování (góly + asistence).
* **Editor týmů:** V nastavení můžeš zakládat týmy a přidávat hráče. Když na hráče v seznamu klikneš dvakrát (double-click), smaže se.

## Jak to ukládá data?
* Týmy a hráči se ukládají sami do souboru `teams.dat`.
* Historie zápasů se sype do textového souboru `history.txt`.

## Spuštění
Všechno startuje z main metody přes úvodní okno:
```java
new WelcomeWindow();