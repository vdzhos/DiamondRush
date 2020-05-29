package source;

public interface Values {

    //window size
    int MAIN_WINDOW_WIDTH = 700;
    int MAIN_WINDOW_LENGTH = 820;

    //game panel (the one where all the actions are performed)
    int GAME_PANEL_WIDTH = 700;
    int GAME_PANEL_LENGTH = 700;
    int GAME_PANEL_X = 0;
    int GAME_PANEL_Y = 100;

    //size of a cell on the map
    int CELL_SIZE = 70;
    int CELL_WIDTH = 70;
    int CELL_LENGTH = 70;

    //status bar with level, number of diamonds
    int GAME_STATUS_BAR_X = 0;
    int GAME_STATUS_BAR_Y = 0;
    int GAME_STATUS_BAR_LENGTH = 120;
    int GAME_STATUS_BAR_WIDTH = 700;

    int PAUSE_BUTTON_X = 25;
    int PAUSE_BUTTON_Y = 25;
    int PAUSE_BUTTON_WIDTH = 50;
    int PAUSE_BUTTON_LENGTH = 50;

    int ENERGY_FIELD_X = 100;
    int ENERGY_FIELD_Y = 45;
    int ENERGY_FIELD_WIDTH = 150;
    int ENERGY_FIELD_LENGTH = 50;

    int PROGRESS_BAR_X = 103;
    int PROGRESS_BAR_Y = 50;
    int PROGRESS_BAR_LENGTH = 40;

    int ENERGY_X = 200;
    int ENERGY_Y = 50;
    int ENERGY_WIDTH = 45;
    int ENERGY_LENGTH = 40;

    int ENERGY_LABEL_X = 120;
    int ENERGY_LABEL_Y = 79;

    int LEVEL_LABEL_X = 117;
    int LEVEL_LABEL_Y = 40;

    int CHECKPOINT_BUTTON_X = 275;
    int CHECKPOINT_BUTTON_Y = 25;
    int CHECKPOINT_BUTTON_WIDTH = 50;
    int CHECKPOINT_BUTTON_LENGTH = 50;

    int GOLD_KEY_X = 340;
    int GOLD_KEY_Y = 22;
    int GOLD_KEY_WIDTH = 65;
    int GOLD_KEY_LENGTH = 70;

    int GOLD_KEY_LABEL_X = 395;
    int GOLD_KEY_LABEL_Y = 90;


    int SILVER_KEY_X = 420;
    int SILVER_KEY_Y = 22;
    int SILVER_KEY_WIDTH = 65;
    int SILVER_KEY_LENGTH = 70;

    int SILVER_KEY_LABEL_X = 475;
    int SILVER_KEY_LABEL_Y = 90;


    int PURPLE_DIAMOND_X = 510;
//    int PURPLE_DIAMOND_Y = 22;
    int PURPLE_DIAMOND_Y = 27;
//    int PURPLE_DIAMOND_WIDTH = 70;
    int PURPLE_DIAMOND_WIDTH = 75;
    int PURPLE_DIAMOND_LENGTH = 65;

    int PURPLE_DIAMOND_LABEL_X = 555;
    int PURPLE_DIAMOND_LABEL_Y = 90;



    int RED_DIAMOND_X = 610 ;
//    int RED_DIAMOND_Y = 22;
    int RED_DIAMOND_Y = 27;
//    int RED_DIAMOND_WIDTH = 70;
    int RED_DIAMOND_WIDTH = 75;
    int RED_DIAMOND_LENGTH = 65;

    int RED_DIAMOND_LABEL_X = 655;
    int RED_DIAMOND_LABEL_Y = 90;

    // pause menu
    int PAUSE_MENU_WIDTH = 400;
    int PAUSE_MENU_LENGTH = 600;

    int PAUSE_MENU_SHIFT_X = 150;
    int PAUSE_MENU_SHIFT_Y = 110;

    int PAUSED_X = 100;
    int PAUSED_Y = 10;

    int PAUSED_WIDTH = 200;
    int PAUSED_LENGTH = 80;

    int RESUME_X = 75;
    int RESUME_Y = 120;

    int RESUME_WIDTH = 250;
    int RESUME_LENGTH = 70;

    int RESTART_X = 75;
    int RESTART_Y = 210;

    int RESTART_WIDTH = 250;
    int RESTART_LENGTH = 70;

    int GOTOMAP_X = 75;
    int GOTOMAP_Y = 300;

    int GOTOMAP_WIDTH = 250;
    int GOTOMAP_LENGTH = 70;

    int SOUND_X = 125;
    int SOUND_Y = 400;

    int SOUND_WIDTH = 50;
    int SOUND_LENGTH = 50;

    int MUSIC_X = 225;
    int MUSIC_Y = 400;

    int MUSIC_WIDTH = 50;
    int MUSIC_LENGTH = 50;

    int CHEST_X = 70;
    int CHEST_Y = 360;

    int CHEST_WIDTH = 280;
    int CHEST_LENGTH = 250;

    // time to wait for previous animation to end
    int TIME_TO_WAIT = 300;

    // delay for animating blinking buttons
    int DELAY_FOR_BUTTONS = 150;

    //map panel
    int LEVEL_POINT_SIZE = 90;

    int LEVEL_1_X = 500;
    int LEVEL_1_Y = 140;

    int LEVEL_2_X = 270;
    int LEVEL_2_Y = 170;

    int LEVEL_3_X = 100;
    int LEVEL_3_Y = 380;

    int LEVEL_4_X = 260;
    int LEVEL_4_Y = 540;

    int LEVEL_5_X = 540;
    int LEVEL_5_Y = 430;

    int MENU_X = 25;
    int MENU_Y = 720;

    int MENU_SIZE = 90;

    // main menu diamond

    int PLAY_X = 225;
    int PLAY_Y = 340;

    int PLAY_WIDTH = 250;
    int PLAY_LENGTH = 90;

    int NEW_GAME_X = 225;
    int NEW_GAME_Y = 540;

    int NEW_GAME_WIDTH = 250;
    int NEW_GAME_LENGTH = 90;

    int EXIT_X = 225;
    int EXIT_Y = 725;

    int EXIT_WIDTH = 250;
    int EXIT_LENGTH = 90;


    //Level ending menu
    int END_LEVEL_BG_WIDTH = 500;
    int END_LEVEL_BG_HEIGHT = 700;
    int END_LEVEL_BG_X = 0;
    int END_LEVEL_BG_Y = 0;

    int END_LEVEL_CONTINUE_WIDTH = 250;
    int END_LEVEL_CONTINUE_HEIGHT = 55;
    int END_LEVEL_CONTINUE_X = 122;
    int END_LEVEL_CONTINUE_Y = 485;

    int END_LEVEL_RESTART_WIDTH = 145;
    int END_LEVEL_RESTART_HEIGHT = 55;
    int END_LEVEL_RESTART_X = 97;
    int END_LEVEL_RESTART_Y = 550;

    int END_LEVEL_GOTOMAP_WIDTH = 145;
    int END_LEVEL_GOTOMAP_HEIGHT = 55;
    int END_LEVEL_GOTOMAP_X = 252;
    int END_LEVEL_GOTOMAP_Y = 550;

    int END_LEVEL_DIAMOND_WIDTH = 300;
    int END_LEVEL_DIAMOND_HEIGHT = 70;
    int END_LEVEL_DIAMOND_X = 100;
    int END_LEVEL_DIAMOND_Y = 160;

    int END_LEVEL_RED_DIAMOND_WIDTH = 300;
    int END_LEVEL_RED_DIAMOND_HEIGHT = 70;
    int END_LEVEL_RED_DIAMOND_X = 100;
    int END_LEVEL_RED_DIAMOND_Y = 240;

    int END_LEVEL_REVIVALS_WIDTH = 300;
    int END_LEVEL_REVIVALS_HEIGHT = 70;
    int END_LEVEL_REVIVALS_X = 100;
    int END_LEVEL_REVIVALS_Y = 320;

    int END_LEVEL_ARTIFACT_PIECE_WIDTH = 300;
    int END_LEVEL_ARTIFACT_PIECE_HEIGHT = 70;
    int END_LEVEL_ARTIFACT_PIECE_X = 100;
    int END_LEVEL_ARTIFACT_PIECE_Y = 400;

    int END_LEVEL_RESULT_SHIFT_X = 178;
    int END_LEVEL_RESULT_SHIFT_Y = 58;




}
