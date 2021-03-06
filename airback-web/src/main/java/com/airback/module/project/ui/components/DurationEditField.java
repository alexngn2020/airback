package com.airback.module.project.ui.components;

import com.airback.core.utils.HumanTime;
import com.airback.core.utils.StringUtils;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.TextField;

/**
 * @author airback Ltd
 * @since 7.0.0
 */
public class DurationEditField extends CustomField<Long> {

    private TextField txtField = new TextField();

    @Override
    protected Component initContent() {
        return txtField;
    }

    @Override
    protected void doSetValue(Long value) {
        if (value != null) {
            HumanTime humanTime = new HumanTime(value);
            txtField.setValue(humanTime.getApproximately());
        }
    }

    @Override
    public Long getValue() {
        String durationStr = txtField.getValue();
        if (StringUtils.isNotBlank(durationStr)) {
            return HumanTime.eval(durationStr).getDelta();
        }
        return null;
    }
}
