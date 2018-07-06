package com.sphenon.basics.data;

/****************************************************************************
  Copyright 2001-2018 Sphenon GmbH

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

public class NamedTyped<T> extends Named<T> {

    public NamedTyped(CallContext context) {
        super(context);
    }

    public NamedTyped(CallContext context, String name, String type, T data) {
        super(context, name, data);
        this.type = type;
    }

    protected String type;

    public String getType (CallContext context) {
        return this.type;
    }

    public void setType (CallContext context, String type) {
        this.type = type;
    }

    public String toString(CallContext context) {
        return super.toString(context) + "<" + this.name + ">";
    }
}

