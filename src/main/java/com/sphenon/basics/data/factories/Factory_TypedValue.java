package com.sphenon.basics.data.factories;

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
import com.sphenon.basics.exception.*;
import com.sphenon.basics.notification.*;
import com.sphenon.basics.customary.*;
import com.sphenon.basics.many.*;

import com.sphenon.basics.data.*;

public class Factory_TypedValue<T> {

    protected TypedValue instance;

    public TypedValue precreate (CallContext context) {
        return this.instance = new TypedValue(context);
    }

    public TypedValue create (CallContext context) {
        TypedValue instance = (this.instance != null ? this.instance : new TypedValue(context));
        this.instance = null;
        instance.setTypeName (context, type_name);
        instance.setValue (context, value);
        return instance;
    }

    public Factory_TypedValue(CallContext context) {
    }

    public Factory_TypedValue(CallContext context, String type_name, T value) {
        this.type_name = type_name;
        this.value = value;
    }

    protected String type_name;

    public String getTypeName (CallContext context) {
        return this.type_name;
    }

    public void setTypeName (CallContext context, String type_name) {
        this.type_name = type_name;
    }

    protected T value;

    public T getValue (CallContext context) {
        return this.value;
    }

    public void setValue (CallContext context, T value) {
        this.value = value;
    }
}
