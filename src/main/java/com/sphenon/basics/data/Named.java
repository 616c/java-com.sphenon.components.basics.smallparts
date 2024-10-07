package com.sphenon.basics.data;

/****************************************************************************
  Copyright 2001-2024 Sphenon GmbH

  Licensed under the Apache License, Version 2.0 (the "License"); you may not
  use this file except in compliance with the License. You may obtain a copy
  of the License at http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
  License for the specific language governing permissions and limitations
  under the License.
*****************************************************************************/

import com.sphenon.basics.context.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Named<T> implements ContextAware {

    public Named(CallContext context) {
    }

    public Named(CallContext context, String name, T data) {
        this.name = name;
        this.data = data;
        this.data_source = null;
    }

    public Named(CallContext context, String name, DataSource<T> data_source) {
        this.name = name;
        this.data = null;
        this.data_source = data_source;
    }

    protected String name;

    public String getName (CallContext context) {
        return this.name;
    }

    public void setName (CallContext context, String name) {
        this.name = name;
    }

    protected T data;

    public T getData (CallContext context) {
        if (this.data == null && this.data_source != null) {
            this.data = this.data_source.get(context);
        }
        return this.data;
    }

    public T defaultData (CallContext context) {
        return null;
    }

    public void setData (CallContext context, T data) {
        this.data = data;
    }

    protected DataSource<T> data_source;

    public DataSource<T> getDataSource (CallContext context) {
        return this.data_source;
    }

    public DataSource<T> defaultDataSource (CallContext context) {
        return null;
    }

    public void setDataSource (CallContext context, DataSource<T> data_source) {
        this.data_source = data_source;
    }

    public String getDataString (CallContext context) {
        return ContextAware.ToString.convert(context, this.data);
    }

    public String toString(CallContext context) {
        return this.name + "=" + this.getDataString(context);
    }

    static public String toString(CallContext context, Named[] named_datas) {
        return com.sphenon.basics.system.StringUtilities.join(context, named_datas, null, "=", null, true);
    }

    static public<T> Map<String,T> toMap(CallContext context, List<Named<T>> list) {
        Map<String,T> map = new HashMap<String,T>();
        for (Named<T> named : list) {
            map.put(named.getName(context), named.getData(context));
        }
        return map;
    }
}

