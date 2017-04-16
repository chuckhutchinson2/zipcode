import { LocationUiPage } from './app.po';

describe('location-ui App', () => {
  let page: LocationUiPage;

  beforeEach(() => {
    page = new LocationUiPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
