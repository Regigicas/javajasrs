/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jarxrs.exceptions;

/**
 *
 * @author regigicas
 */
public class TrabajoJaxRSException extends Exception
{
    public TrabajoJaxRSException()
    {
    }

    public TrabajoJaxRSException(String message)
    {
        super(message);
    }

    public TrabajoJaxRSException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public TrabajoJaxRSException(Throwable cause)
    {
        super(cause);
    }

    public TrabajoJaxRSException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
