# SUBNETTING CLASSE C
# Algoritmo desenvolvido com ajuda da professora Natalia Oliveira

subnetting = [255,255,255]
mascaras = [128,192,224,240,248,252,254,255]
pesos = [128,64,32,16,8,4,2,1]
expoentes_pesos = [7,6,5,4,3,2,1,0]
bits = [0,0,0,0,0,0,0,0]
bits_emprestados = 0

num_subredes = int(input(f"Digite o número de sub-redes que deseja criar: "))

# Enquanto não digitar um número de subredes valido ele pede para entrar com um número válido.
# Detalhe que se for solicitado 1 sub-rede, O bits serão emprestados, por isso alterei pra no mínimo 2
while num_subredes < 2 or num_subredes > 128:
    num_subredes = int(input(f"Digite um número de sub-redes entre 2 e 128: "))
    
# Verifica na lista de pesos qual será o numero de sub redes no intervalo escolhido e dá ao numero de bits 1 emprestados.
for i in range(len(pesos)):
    if num_subredes>=pesos[i] and num_subredes<=pesos[i-1]:
        bits_emprestados = int(expoentes_pesos[i-1])

input(f"Serão manipulados {bits_emprestados} bit(s) como 1 na mascara de rede")

# completa a lista de bits da esquerda para direita preenchendo 1
for i in range(8):
    if bits_emprestados > 0:
        bits[i] = 1
        nova_mascara = int(mascaras[i])
        # Obtendo o Least Significant Bit (LSB) incremento da sub rede
        LSB = int(pesos[i])
    else:
        bits[i] = 0

    bits_emprestados = bits_emprestados - 1

# Nova mascara utilizada para criar 8 sub redes
subnetting.append(nova_mascara)

input(f"Nova máscara: 255.255.255.{nova_mascara} ")
input(f"O incremento entre as sub-redes será: {LSB} ")

# Criando sub redes
if num_subredes <= 128 and num_subredes > 64:
    print(f"\nAs sub-redes serão: ")
    print (f"\n------------------ END REDE ---- END BROADCAST ----------------------------- ENDEREÇOS VALIDOS ----------------")
    j = 1
    for i in range(0,255):
        print (f"\nA {j}º sub-rede será: X.X.X.{i} até X.X.X.{i+1} ------------ 1 End. válidos da {j}º sub-rede: X.X.X.{i} até X.X.X.{i+1}")
        j += 1

    print (f"\n-----------------------------------------------------------------------------------------------------------------")    
else:
    print(f"\nAs sub-redes serão: ")
    print (f"\n------------------ END REDE ---- END BROADCAST ----------------------------- ENDEREÇOS VALIDOS ----------------")
    j = 1
    for i in range(0,255,LSB):
        print (f"\nA {j}º sub-rede será: X.X.X.{i} até X.X.X.{i+(LSB-1)} ------------ End. válidos da {j}º sub-rede: X.X.X.{i+1} até X.X.X.{i+(LSB-2)}")
        j += 1

    print (f"\n-----------------------------------------------------------------------------------------------------------------")