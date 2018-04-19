package hr.com.vgv.verano.props;

import hr.com.vgv.verano.AppContext;


public final class UserInput extends PropsTemplate {

    public UserInput(final AppContext context) {
        super(context.get("userInput"));
    }
}
