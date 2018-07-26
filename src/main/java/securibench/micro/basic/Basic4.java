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
    
    $Id: Basic4.java,v 1.4 2006/04/04 20:00:40 livshits Exp $
 */
package securibench.micro.basic;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import securibench.micro.BasicTestCase;
import securibench.micro.MicroTestCase;

/** 
 *  @servlet description="test path sensitivity just a bit" 
 *  @servlet vuln_count = "1" 
 *  */
public class Basic4 extends BasicTestCase implements MicroTestCase {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String str = req.getParameter("name");
        Random r = new Random();
        int choice = r.nextInt();

        switch (choice) {   
            case 1: break;
            case 2: break;
            case 3: 
                new File(str);    /* BAD */
                break;
            default:
        }
    }
    
    public String getDescription() {
        return "test path sensitivity just a bit";
    }
    
    public int getVulnerabilityCount() {
        return 1;
    }
}