/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servicios;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author alan2
 */
@WebService(serviceName = "ws1")
public class ws1 {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "operation")
    public int operation(@WebParam(name = "parameter") int parameter, @WebParam(name = "parameter1") int parameter1) {
        //TODO write your implementation code here:
        return parameter+parameter1;
    }
    
}
