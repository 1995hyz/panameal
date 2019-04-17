import {createMuiTheme} from "@material-ui/core";

const theme = createMuiTheme({
    palette: {
        primary: {
            light: '#484848',
            main: '#212121',
            dark: '#000000',
            contrastText: '#fff',
        },
        secondary: {
            light: '#ff9d3f',
            main: '#ef6c00',
            dark: '#b53d00',
            contrastText: '#000',
        },
    },
});

export default (theme);