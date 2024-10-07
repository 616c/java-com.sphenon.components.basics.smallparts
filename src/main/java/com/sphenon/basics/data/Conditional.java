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

public class Conditional<T> implements ContextAware {

    public Conditional(CallContext context) {
    }

    public Conditional(CallContext context, String include_regexp, String exclude_regexp, T data) {
        this.include_regexp = include_regexp;
        this.exclude_regexp = exclude_regexp;
        this.data           = data;
        this.data_source    = null;
    }

    public Conditional(CallContext context, String name, DataSource<T> data_source) {
        this.include_regexp = include_regexp;
        this.exclude_regexp = exclude_regexp;
        this.data           = null;
        this.data_source    = data_source;
    }

    protected String include_regexp;

    public String getIncludeRegexp (CallContext context) {
        return this.include_regexp;
    }

    public void setIncludeRegexp (CallContext context, String include_regexp) {
        this.include_regexp = include_regexp;
    }

    protected String exclude_regexp;

    public String getExcludeRegexp (CallContext context) {
        return this.exclude_regexp;
    }

    public void setExcludeRegexp (CallContext context, String exclude_regexp) {
        this.exclude_regexp = exclude_regexp;
    }

    public boolean matches(CallContext context, String string) {
        return (    (    this.include_regexp == null
                      || string.matches(this.include_regexp) == true)
                 && (    this.exclude_regexp == null
                      || string.matches(this.exclude_regexp) == false)
               );
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
        return "(~" + this.include_regexp + "/!~" + this.exclude_regexp + ")=" + this.getDataString(context);
    }

    static public String toString(CallContext context, Conditional[] conditional_datas) {
        return com.sphenon.basics.system.StringUtilities.join(context, conditional_datas, null, ",", null, true);
    }
}

