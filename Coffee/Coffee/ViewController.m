//
//  ViewController.m
//  Coffee
//
//  Created by rektified on 12/26/15.
//  Copyright © 2015 rektified. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)calculate:(id)sender {
    NSLog(@"Calculate Pressed");
    float water = [[self.waterText text] floatValue];
    float ratio = [[self.ratioText text] floatValue];
    
    NSLog(@"water: %f ratio: %f", water, ratio);
    
    float coffee = water / ratio;
    NSLog(@"Coffee: %f", coffee);
    
    NSString *coffeeResult = [NSString stringWithFormat:@"%f", coffee];
    self.coffeeText.text = coffeeResult;
}
@end
