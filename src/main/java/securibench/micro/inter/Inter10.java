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
    
    $Id: Inter10.java,v 1.1 2006/04/21 17:14:26 livshits Exp $
 */
package securibench.micro.inter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import securibench.micro.BasicTestCase;
import securibench.micro.MicroTestCase;

/** 
 *  @servlet description="more complex object sensitivity" 
 *  @servlet vuln_count = "2" 
 *  */
public class Inter10 extends BasicTestCase implements MicroTestCase {
    private static final String FIELD_NAME = "name";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String s1 = req.getParameter(FIELD_NAME);
        
        String s2 = foo(s1);
        String s3 = foo("abc");
 
        new File(s2);                    /* BAD */
        new File(s3);                    /* OK */
    }
    
    private String foo(String s1) {
		return s1.toLowerCase().substring(0, s1.length()-1);
	}
    
    public String getDescription() {
        return "more complex object sensitivity";
    }
    
    public int getVulnerabilityCount() {
        return 1;
    }
}