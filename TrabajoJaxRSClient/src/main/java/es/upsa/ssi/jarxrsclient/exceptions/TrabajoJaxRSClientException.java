/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jarxrsclient.exceptions;

/**
 *
 * @author regigicas
 */
public class TrabajoJaxRSClientException extends Exception
{
    public TrabajoJaxRSClientException()
    {
    }

    public TrabajoJaxRSClientException(String message)
    {
        super(message);
    }

    public TrabajoJaxRSClientException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public TrabajoJaxRSClientException(Throwable cause)
    {
        super(cause);
    }

    public TrabajoJaxRSClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }  
}
