/*
   Copyright 2006 Benjamin Livshits

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
/**
    @author Benjamin Livshits <livshits@cs.stanford.edu>
    
    $Id: Collections4.java,v 1.5 2006/04/04 20:00:41 livshits Exp $
 */
package securibench.micro.collections;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import securibench.micro.BasicTestCase;
import securibench.micro.MicroTestCase;

/** 
 *  @servlet description = "test of iterators" 
 *  @servlet vuln_count = "1" 
 *  */
public class Collections4 extends BasicTestCase implements MicroTestCase {
    private static final String FIELD_NAME = "name";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter(FIELD_NAME);
        LinkedList ll = new LinkedList();
        ll.addLast(name);
        
        for(Iterator iter = ll.iterator(); iter.hasNext();) {
            Object o = iter.next();
            
            new File(o.toString());                    /* BAD */
        }
    }
    
    public String getDescription() {
        return "test of iterators";
    }
    
    public int getVulnerabilityCount() {
        return 1;
    }
}