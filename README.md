# Toll-Management-Persistence

## Descripción
Aplicación Java para gestionar información de un archivo CSV.

## Funcionalidades
- **Cargar CSV**: `base_datos.csv`.
- **Validar Origen/Destino**: Valores deben existir en las columnas 1 y 2 del CSV.
- **Categoría**: Mostrar opciones de la columna 6 basadas en Origen/Destino.
- **Búsqueda Vía**: Opciones únicas de la columna 3 relacionadas con Origen/Destino.
- **Cálculos**:
  - Total Peajes: Cantidad basada en filtros.
  - Total Distancia: Suma de la columna 9 basada en filtros.
  - Total Precio: Suma de la columna 7 basada en filtros.
  - Total Tiempo: Suma de la columna 8 basada en filtros.

## Instalación
1. Clona el repositorio:
    ```sh
    git clone https://github.com/tu_usuario/tu_repositorio.git
    ```
2. Navega al directorio del proyecto:
    ```sh
    cd tu_repositorio
    ```
3. Asegúrate de tener Java instalado (Java 8+).
4. Ejecuta la aplicación:
    ```sh
    javac -d bin src/*.java
    java -cp bin Principal
    ```

## Requisitos
- Java 8+
- Archivo `base_datos.csv`
