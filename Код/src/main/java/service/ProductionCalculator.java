package service;

import entities.Material;
import entities.Product;
import entities.ProductMaterial;
import java.sql.SQLException;

public class ProductionCalculator {
    private final MaterialService materialService;
    private final ProductService productService;
    private final ProductMaterialService productMaterialService;

    public ProductionCalculator(){
        this.materialService = new MaterialService();
        this.productService = new ProductService();
        this.productMaterialService = new ProductMaterialService();
    }

    /**
     * Рассчитывает необходимое количество материала для производства указанного количества продукции.
     *
     * @param productId       Идентификатор типа продукции.
     * @param materialId      Идентификатор типа материала.
     * @param productQuantity Количество получаемой продукции.
     * @param parameter1      Первый параметр продукции (вещественное число).
     * @param parameter2      Второй параметр продукции (вещественное число).
     * @return                Целое количество необходимого материала с учетом брака, или -1 в случае ошибки.
     * @throws SQLException   Если возникает ошибка при взаимодействии с базой данных.
     */
    public int calculateMaterialQuantity(int productId, int materialId, int productQuantity, double parameter1, double parameter2) throws SQLException {
        // Проверка на корректность входных данных
        if (productId <= 0 || materialId <= 0 || productQuantity <= 0 || parameter1 <= 0 || parameter2 <= 0) {
            System.err.println("Ошибка: Некорректные входные данные для расчета материалов.");
            return -1;
        }

        Product product = null;
        Material material = null;
        ProductMaterial productMaterial = null;

        try {
            product = productService.getProductById(productId);
            material = materialService.getMaterialById(materialId);
            productMaterial = productMaterialService.getProductMaterialByProductAndMaterialId(productId, materialId);

        } catch (SQLException e) {
            System.err.println("Ошибка: Ошибка при получении данных из базы данных: " + e.getMessage());
            throw e;
        }


        // Проверка, что все необходимые данные были найдены
        if(product == null){
            System.err.println("Ошибка: Продукт с ID " + productId + " не найден в базе данных.");
            return -1;
        }
        if(material == null){
            System.err.println("Ошибка: Материал с ID " + materialId + " не найден в базе данных.");
            return -1;
        }
        if(productMaterial == null){
            System.err.println("Ошибка: Связь продукта с ID " + productId + " и материала с ID " + materialId + " не найдена в базе данных.");
            return -1;
        }

        // Расчет количества материала на одну единицу продукции
        double materialPerProduct = parameter1 * parameter2 * productMaterial.getCoefficient();

        // Расчет общего количества материала
        double totalMaterial = materialPerProduct * productQuantity;

        // Учет процента брака материала
        double materialWasteRate = 0;
        switch (material.getMaterialType().toLowerCase()) {
            case "дерево":
                materialWasteRate = 0.05; // 5%
                break;
            case "пластик":
                materialWasteRate = 0.02; // 2%
                break;
            case "текстиль":
                materialWasteRate = 0.03; // 3%
                break;
            case "керамика":
                materialWasteRate = 0.04;
                break;
            case "стекло":
                materialWasteRate = 0.07;
                break;
            case "металл":
                materialWasteRate = 0.04;
                break;
            case "краска":
                materialWasteRate = 0.01;
                break;
            case "фурнитура":
                materialWasteRate = 0.005;
                break;
            default:
                materialWasteRate = 0.02;  // По умолчанию 2%
                break;
        }

        // Увеличение количества материала с учетом брака
        totalMaterial = totalMaterial * (1 + materialWasteRate);

        // Округление до целого числа и возврат результата
        int result = (int) Math.ceil(totalMaterial);
        System.out.println("Расчет материалов: productId=" + productId + ", materialId=" + materialId + ", productQuantity=" + productQuantity + ", parameter1=" + parameter1 + ", parameter2=" + parameter2 + ", Result=" + result);
        return result;
    }
}
