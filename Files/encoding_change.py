import pandas as pd

# Try Windows-1250
try:
    df = pd.read_csv("Slownik.csv", encoding="windows-1250", delimiter=";")
    df.to_csv("Slownik_utf8.csv", index=False, encoding="utf-8", sep=";")
    print("Plik poprawnie przekonwertowany z Windows-1250 do UTF-8.")
except Exception as e:
    print("Windows-1250 nie działa, próbuję ISO-8859-2...")
    
    # Try ISO-8859-2
    df = pd.read_csv("Slownik.csv", encoding="ISO-8859-2", delimiter=";")
    df.to_csv("Slownik_utf8.csv", index=False, encoding="utf-8", sep=";")
    print("Plik poprawnie przekonwertowany z ISO-8859-2 do UTF-8.")

print("Nowy plik zapisany jako 'Magazynnp_utf8.csv'")
