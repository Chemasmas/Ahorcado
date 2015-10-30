/*
 * Copyright 2013 - Per Wendel
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package spark.template.freemarker;

import java.io.IOException;
import java.io.StringWriter;

import com.google.gson.Gson;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.ModelAndView;
import spark.ResponseTransformer;
import spark.TemplateEngine;

/**
 * Basado en el FreeMarkerEngine
 *
 * @author Chemasmas
 *
 * @see  FreeMarkerEngine
 */
public class GSONTemplateEngine implements ResponseTransformer {

    Gson gson=new Gson();

    @Override
    public String render(Object o) throws Exception {
        return gson.toJson(o);
    }
}
