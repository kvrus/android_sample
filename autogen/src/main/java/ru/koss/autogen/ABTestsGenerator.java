package ru.koss.autogen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Mustache.TemplateLoader;
import com.samskivert.mustache.Template;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by konstantin on 22.02.18.
 */

public class ABTestsGenerator {

    static final String EXAMPLE_AB_TEST_JSON = "{ \"experimentsVersion\":\"1.0\"," +
            "\"experiments\":[  \n" +
            "{\"experimentName\":\"ShowPromoExperiment\",\n" +
            "          \"variants\":[\n" +
            "            {\n" +
            "                      \"experimentVariantName\":\"A_EXPERIMENT\",\n" +
            "                      \"experimentVariantDescription\":\"a variant\",\n" +
            "                      \"experimentVariantConfigValue\":\"A\",\n" +
            "                      \"experimentValues\":[\n" +
            "                      {\n" +
            "                         \"experimentValueName\":\"shouldBeShown\",\n" +
            "                         \"experimentValueType\":\"String\",\n" +
            "                         \"experimentValue\":\"false\"\n" +
            "                      }\n" +
            "                      ]\n" +
            "            }\n" +
            "          ],\n" +
            "          \"defaultValues\":[\n" +
            "                    {\n" +
            "                      \"experimentValueName\":\"shouldBeShown\",\n" +
            "                      \"experimentValueType\":\"String\",\n" +
            "                      \"experimentValue\":\"true\"\n" +
            "                   }\n" +
            "          ]\n" +
            "    }," +
            "   {  \n" +
            "      \"experimentName\":\"ButtonTextExperiment\",\n" +
            "      \"variants\":[  \n" +
            "         {  \n" +
            "            \"experimentVariantName\":\"A_EXPERIMENT\",\n" +
            "            \"experimentVariantDescription\":\"a variant\",\n" +
            "            \"experimentVariantConfigValue\":\"A\",\n" +
            "            \"experimentValues\":[\n" +
            "            {  \n" +
            "               \"experimentValueName\":\"getButtonColor\",\n" +
            "               \"experimentValueType\":\"String\",\n" +
            "               \"experimentValue\":\"#0000FF\"\n" +
            "            },\n" +
            "            {  \n" +
            "               \"experimentValueName\":\"getButtonText\",\n" +
            "               \"experimentValueType\":\"String\",\n" +
            "               \"experimentValue\":\"blue button\"\n" +
            "            }\n" +
            "            ]\n" +
            "         },\n" +
            "         {  \n" +
            "            \"experimentVariantName\":\"B_EXPERIMENT\",\n" +
            "            \"experimentVariantDescription\":\"b variant\",\n" +
            "            \"experimentVariantConfigValue\":\"B\",\n" +
            "            \"experimentValues\":[  \n" +
            "               {  \n" +
            "                  \"experimentValueName\":\"getButtonColor\",\n" +
            "                  \"experimentValueType\":\"String\",\n" +
            "                  \"experimentValue\":\"#FF0000\"\n" +
            "               },\n" +
            "               {  \n" +
            "                  \"experimentValueName\":\"getButtonText\",\n" +
            "                  \"experimentValueType\":\"String\",\n" +
            "                  \"experimentValue\":\"green button\"\n" +
            "               }\n" +
            "            ]\n" +
            "         },\n" +
            "         {  \n" +
            "            \"experimentVariantName\":\"C_EXPERIMENT\",\n" +
            "            \"experimentVariantDescription\":\"c variant\",\n" +
            "            \"experimentVariantConfigValue\":\"C\",\n" +
            "            \"experimentValues\":[  \n" +
            "               {  \n" +
            "                  \"experimentValueName\":\"getButtonColor\",\n" +
            "                  \"experimentValueType\":\"String\",\n" +
            "                  \"experimentValue\":\"#00FF00\"\n" +
            "               },\n" +
            "               {  \n" +
            "                  \"experimentValueName\":\"getButtonText\",\n" +
            "                  \"experimentValueType\":\"String\",\n" +
            "                  \"experimentValue\":\"red button\"\n" +
            "               }\n" +
            "            ]\n" +
            "         }\n" +
            "      ],\n" +
            "      \"defaultValues\":[  \n" +
            "         {  \n" +
            "            \"experimentValueName\":\"getButtonColor\",\n" +
            "            \"experimentValueType\":\"String\",\n" +
            "            \"experimentValue\":\"#000000\"\n" +
            "         },\n" +
            "         {  \n" +
            "            \"experimentValueName\":\"getButtonText\",\n" +
            "            \"experimentValueType\":\"String\",\n" +
            "            \"experimentValue\":\"def button\"\n" +
            "         }\n" +
            "      ]\n" +
            "   }\n" +
            "]}";

    static final String EXPERIMENT_TEMPLATE =
            "package ru.koss.lib.tests\n" +
            "/**\n" +
            "* THIS CODE WAS AUTOGENERATED\n" +
            "* YOU CANNOT MODIFY THIS FILE.\n" +
            "*/\n" +
            "enum class {{experimentName}} : BaseTest {\n" +
            "{{#variants}}\n" +
            "{{experimentVariantName}} {\n" +
            "override fun getExperimentVariantDescription(): String = \"{{experimentVariantDescription}}\"  \n" +
            "override fun getExperimentValue(): String = \"{{experimentVariantConfigValue}}\" \n" +
            "{{#experimentValues}}\n" +
            "override fun {{experimentValueName}}(): {{experimentValueType}} = \"{{experimentValue}}\" \n" +
            "{{/experimentValues}}\n" +
            "},\n" +
            "{{/variants}}\n" +
            "DEF_EXPERIMENT {\n" +
            "override fun getExperimentVariantDescription(): String = \"default variant\"\n" +
            "override fun getExperimentValue(): String = \"DEF\"\n" +
            "};\n" +
            "override fun getExperimentKey(): String = \"{{experimentName}}\"  \n" +
            "{{#defaultValues}} \n" +
            "open fun {{experimentValueName}}(): {{experimentValueType}} = \"{{experimentValue}}\" \n" +
            "{{/defaultValues}}\n" +
            "}";

    static final String EXPERIMENT_INTERFACE_TEMPLATE ="package ru.koss.lib\n" +
            "\n" +
            "{{#experiments}}\n" +
            "import ru.koss.lib.tests.{{experimentName}}\n" +
            "{{/experiments}}\n" +
            "\n" +
            "/**\n" +
            "* THIS CODE WAS AUTOGENERATED\n" +
            "* YOU CANNOT MODIFY THIS FILE.\n" +
            "*/\n" +
            "interface ABRepository {\n" +
            "{{#experiments}}\n" +
            "open fun get{{experimentName}}(): {{experimentName}}\n" +
            "{{/experiments}}\n" +
            "}";

    static final String EXPERIMENT_INTERFACE_IMPL_TEMPLATE ="package ru.koss.lib\n" +
            "\n" +
            "import ru.koss.lib.config.ABConfig\n" +
            "{{#experiments}}\n" +
            "import ru.koss.lib.tests.{{experimentName}}\n" +
            "{{/experiments}}\n" +
            "/**\n" +
            "* THIS CODE WAS AUTOGENERATED\n" +
            "* YOU CANNOT MODIFY THIS FILE.\n" +
            "*/\n" +
            "class ABRepositoryImpl(private val version: String, private val config: ABConfig) : ABRepository {\n" +
            "\n" +
            "init {\n" +
            "if(version != \"{{experimentsVersion}}\") {\n" +
            "throw IllegalStateException(\"Please check your AB testing configuration seems like you forgot to update code with new tests changes !\")\n" +
            "}\n" +
            "}\n" +
            "\n" +
            "{{#experiments}}\n" +
            "override fun get{{experimentName}}(): {{experimentName}} {\n" +
            "val value = config.getValue({{experimentName}}.DEF_EXPERIMENT.getExperimentKey(),\"\")\n" +
            "return when (value) {\n" +
            "{{#variants}}\n" +
            "{{experimentName}}.{{experimentVariantName}}.getExperimentValue() -> {{experimentName}}.{{experimentVariantName}}\n" +
            "{{/variants}}\n" +
            "else -> {{experimentName}}.DEF_EXPERIMENT\n" +
            "}\n" +
            "}\n" +
            "{{/experiments}}\n" +
            "\n" +
            "}";

    /**
     * Given that this is a separate program from the android app, we have to use
     * a static main java method to create the ab test classes files.
     *
     * @param args
     * @throws IOException
     * @throws SQLException
     */
    public static void main(String[] args) throws IOException, SQLException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        ExperimentsJson data = gson.fromJson(EXAMPLE_AB_TEST_JSON, ExperimentsJson.class);
        String inter = getExperiemntInterfaceClass(data);
        String impl = getExperiemntInterfaceImplClass(data);
        writeClass("lib/src/main/java/ru/koss/lib/ABRepository", inter);
        writeClass("lib/src/main/java/ru/koss/lib/ABRepositoryImpl", impl);

        for(ExperimentJson testJson: data.experiments) {

            String exp = getExperiemntClass( testJson );

            writeClass("lib/src/main/java/ru/koss/lib/tests/" + testJson.experimentName, exp);

        }

    }

    static public String getExperiemntClass(final ExperimentJson agExperiment) throws IOException {
        String mainTemplate = EXPERIMENT_TEMPLATE;
        final Map<String, String> partials = new HashMap<>();
        partials.put("experimentName", agExperiment.experimentName);
        final Mustache.Compiler compiler = Mustache.compiler().withLoader(new TemplateLoader() {

            @Override
            public Reader getTemplate(String name) throws Exception {
                return new StringReader(partials.get(name));
            }
        });
        final Template template = compiler.compile(mainTemplate);
        return template.execute(agExperiment);
    }

    static public String getExperiemntInterfaceClass(final ExperimentsJson agExperiment) throws IOException {
        String mainTemplate = EXPERIMENT_INTERFACE_TEMPLATE;
        final Map<String, String> partials = new HashMap<>();
        partials.put("experimentsVersion", agExperiment.experimentsVersion);
        final Mustache.Compiler compiler = Mustache.compiler().withLoader(new TemplateLoader() {
            @Override
            public Reader getTemplate(String name) throws Exception {
                return new StringReader(partials.get(name));
            }
        });
        final Template template = compiler.compile(mainTemplate);
        return template.execute(agExperiment);
    }

    static public String getExperiemntInterfaceImplClass(final ExperimentsJson agExperiment) throws IOException {
        String mainTemplate = EXPERIMENT_INTERFACE_IMPL_TEMPLATE;
        final Map<String, String> partials = new HashMap<>();
        partials.put("experimentsVersion", agExperiment.experimentsVersion);
        final Mustache.Compiler compiler = Mustache.compiler().withLoader(new TemplateLoader() {

            @Override
            public Reader getTemplate(String name) throws Exception {
                return new StringReader(partials.get(name));
            }
        });
        final Template template = compiler.compile(mainTemplate);
        return template.execute(agExperiment);
    }

    static public void writeClass(String clazzName, String clazz) {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(clazzName + ".kt"), "utf-8"));
            writer.write(clazz);
        } catch (IOException ex) {
            // report
        } finally {
            try {writer.close();} catch (Exception ex) {/*ignore*/}
        }
    }

    static public class ExperimentInterface {
        public String  experimentsVersion;
        public List<ExperimentImport> experimentInterfaces;
    }

    static public class ExperimentImport {
        public String  experimentName;
        public List<ExperimentVariantInterface> experimentVariants;
    }

    static public class ExperimentVariantInterface {
        public String  experimentName;
        public String  experimentVariantName;
    }

    static public class ExperimentsJson {
        public String  experimentsVersion;
        public List<ExperimentJson> experiments;
    }

    static public class ExperimentJson {
        public String  experimentName;
        public List<ExperimentJsonVariant> variants;
        public List<ExperimentJsonVariantValues> defaultValues;
    }

    public class ExperimentJsonVariant {
        public String  experimentVariantName;
        public String  experimentVariantDescription;
        public String  experimentVariantConfigValue;
        public List<ExperimentJsonVariantValues> experimentValues;
    }

    public class ExperimentJsonVariantValues {
        public String  experimentValueName;
        public String  experimentValueType;
        public String  experimentValue;
    }

}
