<%@ page import="grails.plugin.databinding.BindingUtils" %>

<rendering:registerFormat name="removethousandseparator" closure="{ value, attrs ->
    def formattedValue = value instanceof Number ? String.format('%.0f', value) : value
    "${formattedValue}"
                          }" />
