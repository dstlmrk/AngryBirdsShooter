# Semestrální práce

![GitHub Logo](/images/shooter.png)

* MVC hra
* výběr z vlastností:
    * změna síly střely (force),
    * úhlu kanónu (angle),
    * gravitace (gravity),
    * počítání score,
    * možnost vystřelit více střel najednou
    * ...

## Požadavky na stav semestrální práce po 3. cvičení

* model nezávislý na view a controlleru (tzn na grafice, swingu)
    * jinými slovy, model musí být testovatelný, spustitelný i bez view a controlleru
* správný návrh hierarchie tříd (entit) v modelu (příklad: viz UML diagram z 1. cvičení)
* třída Model neobsahuje herní logiku, logika je rozmístěna do příslušných tříd (missile.move() etc)
    * třída Model tedy pouze drží reference na ostatní herní objekty a slouží jako fasáda
* MVC včetně vzoru Observer použitého pro notifikaci Model ⇒ View
* návrhový vzor Visitor při vykreslování entit (model je nezávislý na View, takže entity neznají metody grafické knihovny)

## Požadavky na stav semestrální práce po 4. cvičení

Aplikace bude obsahovat použité následující návrhové vzory. Připojuji i příklady, jak je použít.

* Strategy
    * Missile bude mít 2 strategie pohybu: simple a realistic. Simple je základní šikmý vrh (tak jak bylo do teď implementováno), realistická simuluje balistickou křivku (matematicky si můžete výpočet zjednodušit jak chcete)
* State
    * Cannon bude mít dva stavy: single shooting mode a double shooting mode (uživatel např. přehazuje stistkem tlačítka, nebo se stavy mohou automaticky střídat samy). V double shooting modu vystřelí kanon najednou vždy 2 střely, s určitým rozptylem
* AbstractFactory
    * při startu aplikace se určí (buď hard-coded proměnnou, nebo command-line-argumentem), zda se hra pustí v simple nebo realistic módu
    * v simple mode bude factory vytvářet simple missile (viz výše) a simple enemy, kde simple enemy nemění svou pozici
    * v realistic mode bude factory vytvářet realistic missile (viz výše) a realistic enemy, kde realistic enemy nějakým způsobem mění svou pozici

## Požadavky na stav semestrální práce po 5. cvičení

* aplikace bude obsahovat návrhový vzor Memento (např. na ukládání stavu modelu)
* v projektu bude alespoň 5 unit testů (test cases) v alespoň 2 test suitech a bude použito mockování
