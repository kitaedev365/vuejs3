import { test, expect } from '@playwright/test';
test.describe('BoardPost Component', () => {
  test.beforeEach(async ({ page }) => {
    await page.goto('/');
  });
  test('should render empty form fields', async ({ page }) => {
    const titleInput = await page.locator('[data-testid=title-input]');
    const contentInput = await page.locator('[data-testid=content-input]');
    await expect(titleInput).toHaveValue('');
    await expect(contentInput).toHaveValue('');
  });
  test('should show error message if fields are empty', async ({ page }) => {
    await page.click('[data-testid=submit-button]');
    const message = await page.locator('[data-testid=message]');
    await expect(message).toHaveText('Please fill out all fields.');
  });
  test('should display success message when fields are filled', async ({ page }) => {
    await page.fill('[data-testid=title-input]', 'Sample Title');
    await page.fill('[data-testid=content-input]', 'Sample Content');
    await page.click('[data-testid=submit-button]');
    const message = await page.locator('[data-testid=message]');
    await expect(message).toHaveText('Post created successfully!');
    await expect(page.locator('[data-testid=title-input]')).toHaveValue('');
    await expect(page.locator('[data-testid=content-input]')).toHaveValue('');
  });
});