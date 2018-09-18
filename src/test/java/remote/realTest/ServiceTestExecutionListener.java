package remote.realTest;

import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.util.fileloader.XlsDataFileLoader;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

public class ServiceTestExecutionListener implements TestExecutionListener {
    private IDatabaseTester databaseTester;

    @Override
    public void afterTestClass(TestContext testContext) {

    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        if(databaseTester != null) {
            databaseTester.onTearDown();
        }
    }

    @Override
    public void beforeTestClass(TestContext testContext) {

    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        DataSets dataSets = testContext.getTestMethod().getAnnotation(DataSets.class);
        if(dataSets == null) {
            return;
        }

        String dataSetName = dataSets.setUpDataSet();
        if(!dataSetName.isEmpty()) {
            databaseTester = testContext.getApplicationContext().getBean("databaseTester", IDatabaseTester.class);
            XlsDataFileLoader xlsDataFileLoader = testContext.getApplicationContext().getBean("xlsDataFileLoader", XlsDataFileLoader.class);
            IDataSet dataSet = xlsDataFileLoader.load(dataSetName);
            databaseTester.setDataSet(dataSet);
            databaseTester.onSetup();
        }
    }

    @Override
    public void prepareTestInstance(TestContext testContext) {

    }
}
