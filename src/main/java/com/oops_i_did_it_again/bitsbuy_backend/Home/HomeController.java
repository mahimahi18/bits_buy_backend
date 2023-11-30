package Home;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mysqlaccess.Products;
import Home.HomeQuery;
import net.minidev.json.JSONObject;
import Home.ProductDTO;

@RestController
public class HomeController {
    @Autowired 
    private HomeQuery homeQuery;

    @GetMapping("/home")
    @ResponseBody
    public JSONObject Home(@RequestParam("pageNumber") int pageNumber){
       List<Products> productDetails = homeQuery.selectProducts();

        // Convert the list of Products to a list of ProductDTO
        List<ProductDTO> productDTOList = productDetails.stream()
                .map(product -> new ProductDTO(product.getProductName(), product.getBid()))
                .collect(Collectors.toList());

        // Create a JSON object to hold the list
        JSONObject response = new JSONObject();
        response.put("products", productDTOList);

        return response;
    }
    
}
