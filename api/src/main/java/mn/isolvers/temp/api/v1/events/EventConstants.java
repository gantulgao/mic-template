package mn.isolvers.temp.api.v1.events;

@SuppressWarnings("unused")
public interface EventConstants {

  String DESTINATION = "template-v1";
  String SELECTOR_NAME = "action";
  String INITIALIZE = "initialize";
  String POST_SAMPLE = "post-sample";
  String SELECTOR_INITIALIZE = SELECTOR_NAME + " = '" + INITIALIZE + "'";
  String SELECTOR_POST_SAMPLE = SELECTOR_NAME + " = '" + POST_SAMPLE + "'";
}
