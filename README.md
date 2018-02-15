PigCoins_JuananPujals
---

Examen Programación Práctico

Historias de usuario:

1.- Crear Wallet (constructor y metodo generatePairKey).
	WHO? El usuario.
	WHAT? Desde la app "apreta" botón para autoasignar el PublicKey y el privateKey de su wallet.
	WHY? Para poder "comerciar"/hacer transaccions con pigcoins.
2.- Wallet LoadCoins 
    Who? El usuario
    WHAT? Desde de la app de su Wallet, le da a un boton (actualizar) para cargar el total de pigCoins que tiene : totalnputs(entradas) - totalOutputs(salidas).
    WHY? Para conocer cuantos pigCoins le quedan en su Wallet.

3.-  Wallet sendCoins 
    WHO? El usuario
    WHAT? elige los pigcpoins que quiere enviar y selecciona la transaccion
    WHY? Para madarlos a la wallet de otro usuario 

4.- WalletCollectCoins
    WHO? El usuario
    WHAT? recarga la app para obtener el total de pigCoins entre tu input total y output toal.
    WHY? Para calcular el total de pigcoins de los que dispone --> loadCoins()

5.- Block Chain - addOrigin
    WHO? Administrador o el "mismo" sistema
    WHAT? añade varuas transacciones a la lista como originales
    WHY? para que no se puede romper la cadena del blockChain. 