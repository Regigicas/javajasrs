/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jarxrs.json;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.adapter.JsonbAdapter;
import javax.ws.rs.core.Link;

/**
 *
 * @author regigicas
 */
public class LinkJsonAdapter implements JsonbAdapter<Link, JsonObject>
{

    @Override
    public JsonObject adaptToJson(Link orgnl) throws Exception
    {
        return Json.createObjectBuilder()
                .add("href", orgnl.getUri().toString())
                .add("rel", orgnl.getRel())
                .add("type", orgnl.getType())
                .build();
    }

    @Override
    public Link adaptFromJson(JsonObject adptd) throws Exception
    {
        return null;
    }
}
