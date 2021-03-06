/*
 * Copyright (c)2015 Elsevier, Inc.

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.elsevier.spark.examples.xquery;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.spark.api.java.function.VoidFunction;

import scala.Tuple2;

import com.elsevier.xml.XQueryProcessor;

/**
 * This class will be executed by a 'worker' in the Spark cluster.  The focus of this class
 * is to initialize the namespace mappings (if necessary).
 * 
 * @author mcbeathd
 *
 */
public class XQueryInitWorker  implements VoidFunction<Iterator<Tuple2<String,String>>> {

	private HashMap<String,String> namespaceMappings;
	
	
	/**
	 * Init the namespace mappings.
	 * 
	 * @param namespaceMappingsBucket
	 * @param namespaceMappingsKey
	 */
	public XQueryInitWorker(HashMap<String,String> namespaceMappings)  {
		this.namespaceMappings = namespaceMappings;
	}


	@Override
	public void call(Iterator<Tuple2<String, String>> arg0) throws IOException  {
		XQueryProcessor.clear();
		XQueryProcessor.init(namespaceMappings);		
	}
	
}
