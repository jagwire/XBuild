/*
 * The MIT License
 *
 * Copyright 2014 Ryan.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package sample;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Ryan
 */
abstract class WebPortal  extends ProjectConfiguration {
    
    private String modelPath;
    private String controllerPath;
    private String viewsPath;
    private String projectName;
    private int majorVersion;
    private int minorVersion;
    private Class<? extends WebController> rootControllerClass;
    
    private Map<VERB, Map<String, Class>> endpoints;
    private Set<String> resources;
    
    enum VERB {
      GET, POST, PUT, DELETE;  
    };
    
    //for project configuration purposes
    protected void models(String modelPath) {
        this.modelPath = modelPath;
    }
    protected void controllers(String controllerPath) {
        this.controllerPath = controllerPath;
    }
    protected void views(String viewsPath) {
        this.viewsPath = viewsPath;
    }
    
    //for portal configuration purposes
    public abstract void app();
    protected void portal(String appName) {
        this.projectName = appName;
    }
    protected void version(int major, int minor) {
        this.majorVersion = major;
        this.minorVersion = minor;
    }
    
    //for portal implementation
    public abstract void routes();
    protected void root(Class<? extends WebController> rootControllerClass) {
        this.rootControllerClass = rootControllerClass;
        
    }
    protected Endpoint resource(String resource) { return null; }
    protected Endpoint resource(String resource, Endpoint... subResources) { return null; }

    protected void GET(String path, Class c) {}
    protected void POST(String path, Class c) { }
    protected void PUT(String path, Class c) { }
    protected void DELETE(String path,  Class c) { }
    
    
    class Endpoint {
        private Map<String, Endpoint> children;
        private String path;
        public Endpoint(String path) {
            this.path = path;
            children = new HashMap<>();
        }
        
        public void addEndpoint(Endpoint e) {
            children.put(e.getPath(), e);
        }
        
        public String getPath() {
            return this.path;
        }
    }
}
