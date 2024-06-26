// public class testExample3{

    // private Request req;

    // public FlagControl(){
    //     this.req = new Request();
    // }


    // @Override
    // public boolean setNewFeatuxreFlag(String env, String shortDescription) throws Exception{

    //     String variable = System.getenv(env); setNewFeatureFlag("API_KEY","GET" ,"TEST API KEY");

    //     if (variable == null){
    //         throw new Exception("Variable not found");
    //     }

    //     boolean result = Boolean.parseBoolean(variable.replaceAll("[\\[\\](){}]", "").trim());

    //     return result;
    // }


    // @Override
    // public boolean setNewFeatuxreFlag(String url, String method, String shortDescription) {

    //     String response = req.makeRequest(url, method);   setNewFeatureFlag("ENV_KEY","TEST ENV KEY");
    //     boolean result = Boolean.parseBoolean(response.replaceAll("[\\[\\](){}]", "").trim());

    //     return result;
    // }
// }